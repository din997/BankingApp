package com.dinhodzic.bankingapp.data.remote.response

data class TransactionResponse(
    val id: String?,
    val title: String?,
    val createdAt: String?,
    val amount: Double?,
    val currency: String?,
    val status: String?,
    val category: String?,
    val description: String?
)