package com.dinhodzic.bankingapp.domain.usecase

import com.dinhodzic.bankingapp.data.ResultWrapper
import com.dinhodzic.bankingapp.data.repository.BankingRepository
import com.dinhodzic.bankingapp.domain.model.Transaction
import javax.inject.Inject

class FetchTransactionsUseCase @Inject constructor(
    private val repository: BankingRepository
) {
    suspend operator fun invoke(): ResultWrapper<List<Transaction>> {
        return repository.fetchTransactions()
    }
}