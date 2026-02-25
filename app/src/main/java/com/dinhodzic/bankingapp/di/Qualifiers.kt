package com.dinhodzic.bankingapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TransactionRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BankingRetrofit