package com.dinhodzic.bankingapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhodzic.bankingapp.data.ResultWrapper
import com.dinhodzic.bankingapp.domain.model.BankCard
import com.dinhodzic.bankingapp.domain.usecase.FetchCreditCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCreditCardsUseCase: FetchCreditCardsUseCase
): ViewModel() {

    private val _state = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.Loading)
    val state = _state.asStateFlow()

    init {
        fetchCreditCards()
    }

    private fun fetchCreditCards() = viewModelScope.launch {
        val response = fetchCreditCardsUseCase.invoke()
        when (response) {
            is ResultWrapper.Success -> {
                _state.value = HomeScreenUiState.Success(response.value)
            }
            is ResultWrapper.Error -> {
                _state.value = HomeScreenUiState.Empty
            }
        }
    }
}

sealed class HomeScreenUiState {
    object Loading : HomeScreenUiState()
    data class Success(val creditCards: List<BankCard>) : HomeScreenUiState()
    object Empty : HomeScreenUiState()

}