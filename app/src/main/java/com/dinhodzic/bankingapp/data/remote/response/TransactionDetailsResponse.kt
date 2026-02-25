package com.dinhodzic.bankingapp.data.remote.response

data class TransactionDetailsResponse(
    val createdAt: String?,
    val name: String?,
    val street: String?,
    val city: String?,
    val phone: String?,
    val transactionId: String?,
    val transactionDate: String?,
    val amount: String?,
    val id: String?
)
