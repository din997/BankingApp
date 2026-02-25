package com.dinhodzic.bankingapp.di

import com.dinhodzic.bankingapp.BuildConfig
import com.dinhodzic.bankingapp.data.remote.BankingApiService
import com.dinhodzic.bankingapp.data.remote.TransactionsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @BankingRetrofit
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_MAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @TransactionRetrofit
    fun provideTransactionRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_TRANSACTIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBankingApiService(@BankingRetrofit retrofit: Retrofit): BankingApiService {
        return retrofit.create(BankingApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionApiService(@TransactionRetrofit retrofit: Retrofit): TransactionsApiService {
        return retrofit.create(TransactionsApiService::class.java)
    }
}