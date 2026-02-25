package com.dinhodzic.bankingapp.domain.model

import java.time.LocalDate

data class Transaction(
    val id: String?,
    val title: String?,
    val date: LocalDate?,
    val amount: Double?,
    val currency: String?,
    val status: TransactionStatus?,
    val category: TransactionCategory?,
    val description: String?
)

enum class TransactionStatus {
    SUCCESS,
    FAILED
}

enum class TransactionCategory {
    ELECTRIC,
    WATER,
    MOBILE
}