package com.dinhodzic.bankingapp.domain.repository

import android.util.Log
import com.dinhodzic.bankingapp.data.ResultWrapper
import com.dinhodzic.bankingapp.data.mapper.toDomain
import com.dinhodzic.bankingapp.data.remote.BankingApiService
import com.dinhodzic.bankingapp.data.remote.TransactionsApiService
import com.dinhodzic.bankingapp.data.repository.BankingRepository
import com.dinhodzic.bankingapp.domain.model.BankCard
import com.dinhodzic.bankingapp.domain.model.BankingProfile
import com.dinhodzic.bankingapp.domain.model.Transaction
import com.dinhodzic.bankingapp.domain.model.TransactionDetail
import javax.inject.Inject
import kotlin.collections.isNullOrEmpty

class BankingRepositoryImpl @Inject constructor(
    private val apiService: BankingApiService,
    private val transactionsApiService: TransactionsApiService
) : BankingRepository {
    override suspend fun fetchUserProfile(id: Int): ResultWrapper<BankingProfile> {
        return try {
            val response = apiService.getProfile()
            if (response.isSuccessful && response.body() != null) {
                val profileResponse = response.body()?.first()
                val bankingProfile = BankingProfile(
                    profileResponse?.id ?: 0,
                    profileResponse?.name ?: "",
                    profileResponse?.avatar ?: "",
                    profileResponse?.country ?: "",
                    profileResponse?.street ?: ""
                )
                ResultWrapper.Success(bankingProfile)
            } else {
                Log.e("USERINFO", "user info error ${response.errorBody()}")
                ResultWrapper.Error(errorMessage = "Failure to load profile")
            }
        } catch (e: Exception) {
            Log.e("USERINFO", "user info error $e")
            ResultWrapper.Error()
        }
    }

    override suspend fun fetchBankCards(): ResultWrapper<List<BankCard>> {
        return try {
            val response = apiService.getCards()
            if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                val creditCards = response.body()?.map {
                    BankCard(
                        clientName = it.clientName,
                        name = it.name,
                        iban = it.iban,
                        available = it.avaliable,
                        currency = it.currency
                    )
                } ?: listOf()
                ResultWrapper.Success(creditCards)
            } else {
                Log.e("BANKCARD", "Bank card fetch failure")
                ResultWrapper.Error(errorMessage = "Failure to load credit cards")
            }
        } catch (e: Exception) {
            Log.e("BANKCARD", "Bank card fetch failure $e")
            ResultWrapper.Error()
        }
    }

    override suspend fun fetchTransactions(): ResultWrapper<List<Transaction>> {
        return try {
            val response = transactionsApiService.getTransactions()
            Log.e("TRANSACTION", "Transaction fetch ${response}")
            if (response.isSuccessful && response.body() != null) {
                val transactions = response.body()?.map {
                    it.toDomain()
                } ?: emptyList()
                ResultWrapper.Success(transactions)
            } else {
                Log.e("TRANSACTION", "Transaction fetch failure empty list")
                ResultWrapper.Error()
            }
        } catch (e: Exception) {
            Log.e("TRANSACTION", "Transaction fetch failure $e")
            ResultWrapper.Error()
        }
    }

    override suspend fun fetchTransactionDetails(id: String): ResultWrapper<TransactionDetail> {
        return try {
            val response = transactionsApiService.getTransactionDetail(id)
            Log.e("TRANSACTION DETAIL", "Transaction fetch failure ${response}")
            if (response.isSuccessful && response.body() != null) {
                ResultWrapper.Success(response.body()!!.toDomain())
            } else {
                ResultWrapper.Error()
            }

        } catch (e: Exception) {
            Log.e("TRANSACTION DETAIL", "Transaction fetch failure $e")
            ResultWrapper.Error()
        }
    }
}