package com.dinhodzic.bankingapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhodzic.bankingapp.data.ResultWrapper
import com.dinhodzic.bankingapp.data.UserInfoProvider
import com.dinhodzic.bankingapp.data.local.SessionManager
import com.dinhodzic.bankingapp.domain.model.BankingProfile
import com.dinhodzic.bankingapp.domain.usecase.FetchUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val userInfoProvider: UserInfoProvider,
    private val fetchUserProfileUseCase: FetchUserProfileUseCase
    ) : ViewModel() {

    val authState: StateFlow<AuthState> = sessionManager.isLoggedIn
        .map { isLoggedIn ->
            if (isLoggedIn) AuthState.AUTHORIZED else AuthState.UNAUTHORIZED
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AuthState.IDLE
        )

    var profileInfo = userInfoProvider.userInfo.map {
        if (it == BankingProfile.EMPTY) {
            fetchAndSetUserInfo()
        }
        it
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BankingProfile.EMPTY)

    private fun fetchAndSetUserInfo() {
        viewModelScope.launch {
            val result = fetchUserProfileUseCase.invoke(0)
            when (result) {
                is ResultWrapper.Success -> {
                    userInfoProvider.setProfileInfo(result.value)
                }

                is ResultWrapper.Error -> {
                    Log.e("USERINFO", "user info error")
                }
            }
        }
    }

}
enum class AuthState {
    AUTHORIZED,
    UNAUTHORIZED,
    IDLE
}