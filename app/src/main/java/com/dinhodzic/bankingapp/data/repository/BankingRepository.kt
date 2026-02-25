package com.dinhodzic.bankingapp.data.repository

import com.dinhodzic.bankingapp.data.ResultWrapper
import com.dinhodzic.bankingapp.domain.model.BankCard
import com.dinhodzic.bankingapp.domain.model.BankingProfile
import com.dinhodzic.bankingapp.domain.model.Transaction
import com.dinhodzic.bankingapp.domain.model.TransactionDetail

interface BankingRepository {
    suspend fun fetchUserProfile(id: Int): ResultWrapper<BankingProfile>

    suspend fun fetchBankCards(): ResultWrapper<List<BankCard>>

    suspend fun fetchTransactions(): ResultWrapper<List<Transaction>>

    suspend fun fetchTransactionDetails(id: String): ResultWrapper<TransactionDetail>
}