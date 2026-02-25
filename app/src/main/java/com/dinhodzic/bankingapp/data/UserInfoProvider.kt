package com.dinhodzic.bankingapp.data

import android.util.Log
import com.dinhodzic.bankingapp.domain.model.BankingProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInfoProvider @Inject constructor() {
    private val _userInfo = MutableStateFlow(BankingProfile.EMPTY)
    val userInfo = _userInfo.asStateFlow()
    fun setProfileInfo(profileInfo: BankingProfile){
        Log.e("USERINFO", "Setting user info ${profileInfo}")
        _userInfo.value = profileInfo
    }
}