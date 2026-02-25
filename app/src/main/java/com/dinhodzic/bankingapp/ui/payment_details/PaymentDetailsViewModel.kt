package com.dinhodzic.bankingapp.ui.payment_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhodzic.bankingapp.data.ResultWrapper
import com.dinhodzic.bankingapp.domain.model.TransactionDetail
import com.dinhodzic.bankingapp.domain.usecase.FetchTransactionDetailUseCase
import com.dinhodzic.bankingapp.ui.navigation.transactionId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.checkNotNull

@HiltViewModel
class PaymentDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val fetchTransactionDetailUseCase: FetchTransactionDetailUseCase
): ViewModel() {

    private val transactionNumber: String = checkNotNull(savedStateHandle[transactionId])

    private val _state = MutableStateFlow<TransactionDetailsUiState>(TransactionDetailsUiState.Loading)
    val state = _state.asStateFlow()

    init {
        fetchTransactionDetail(transactionNumber)
    }

    private fun fetchTransactionDetail(id: String) = viewModelScope.launch {
        _state.value = TransactionDetailsUiState.Loading
        when (val response = fetchTransactionDetailUseCase.invoke(id)) {
            is ResultWrapper.Success -> {
                _state.value = TransactionDetailsUiState.Success(response.value)
            }
            is ResultWrapper.Error -> {
                Log.e("TRANSACTIONDETAIL", "Transaction detail fetch error")
                _state.value = TransactionDetailsUiState.Error(response.errorMessage)
            }
        }
    }
}

sealed class TransactionDetailsUiState {
    object Loading : TransactionDetailsUiState()
    data class Success(val transaction: TransactionDetail) : TransactionDetailsUiState()
    data class Error(val errorMessage: String?) : TransactionDetailsUiState()
}