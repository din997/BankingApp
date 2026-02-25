package com.dinhodzic.bankingapp.ui.payment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinhodzic.bankingapp.data.ResultWrapper
import com.dinhodzic.bankingapp.domain.model.Transaction
import com.dinhodzic.bankingapp.domain.model.TransactionCategory
import com.dinhodzic.bankingapp.domain.usecase.FetchTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentHistoryViewModel @Inject constructor(
    private val fetchTransactionsUseCase: FetchTransactionsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(TransactionsUiState(isLoading = true))
    val uiState: StateFlow<TransactionsUiState> = _uiState

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            try {
                when (val data = fetchTransactionsUseCase.invoke()) {
                    is ResultWrapper.Success -> {
                        Log.e("TRANSACTIONS", "loading transactions ${data.value}")
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                transactions = data.value
                            )
                        }
                    }

                    is ResultWrapper.Error -> {
                        Log.e("TRANSACTIONS", "loading transactions error ${data.errorMessage}")
                    }
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Failed to load transactions"
                    )
                }
            }
        }
    }

    fun onCategorySelected(category: TransactionCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
    }
}

data class TransactionsUiState(
    val isLoading: Boolean = false,
    val selectedCategory: TransactionCategory = TransactionCategory.ELECTRIC,
    val transactions: List<Transaction> = emptyList(),
    val error: String? = null
)