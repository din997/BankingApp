package com.dinhodzic.bankingapp.data.remote

import com.dinhodzic.bankingapp.data.remote.response.BankCardResponse
import com.dinhodzic.bankingapp.data.remote.response.BankingProfileResponse
import com.dinhodzic.bankingapp.data.remote.response.TransactionResponse
import retrofit2.Response
import retrofit2.http.GET

interface BankingApiService {
    @GET("Dashboard")
    suspend fun getProfile(
    ): Response<List<BankingProfileResponse>>

    @GET("Cards")
    suspend fun getCards(): Response<List<BankCardResponse>>

}