package com.dinhodzic.bankingapp.domain.usecase

import com.dinhodzic.bankingapp.data.ResultWrapper
import com.dinhodzic.bankingapp.data.repository.BankingRepository
import com.dinhodzic.bankingapp.domain.model.TransactionDetail
import javax.inject.Inject

class FetchTransactionDetailUseCase @Inject constructor(
    private val repository: BankingRepository
) {
    suspend operator fun invoke(id: String): ResultWrapper<TransactionDetail> {
        return repository.fetchTransactionDetails(id)
    }
}