package com.dinhodzic.bankingapp.domain.model

data class BankCard(
    val clientName: String?,
    val name: String?,
    val available: String?,
    val iban: String?,
    val currency: String?
)
