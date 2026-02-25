package com.dinhodzic.bankingapp.data.remote.response

data class BankCardResponse(
    val name: String?,
    val avaliable: String?,
    val currency: String?,
    val clientName: String?,
    val id: Int,
    val account: String?,
    val createdAt: String?,
    val iban: String?
)
