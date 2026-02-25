package com.dinhodzic.bankingapp.data.remote

import com.dinhodzic.bankingapp.data.remote.response.TransactionDetailsResponse
import com.dinhodzic.bankingapp.data.remote.response.TransactionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TransactionsApiService {
    @GET("Transactions")
    suspend fun getTransactions(): Response<List<TransactionResponse>>

    @GET("TransactionDetail/{id}")
    suspend fun getTransactionDetail(@Path("id") id: String): Response<TransactionDetailsResponse>
}