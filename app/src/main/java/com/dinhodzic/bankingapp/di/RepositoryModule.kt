package com.dinhodzic.bankingapp.di

import com.dinhodzic.bankingapp.data.repository.BankingRepository
import com.dinhodzic.bankingapp.domain.repository.BankingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBankingRepository(
        bankingRepositoryImpl: BankingRepositoryImpl
    ): BankingRepository
}