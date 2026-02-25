package com.dinhodzic.bankingapp.domain.model

import java.time.LocalDate

data class TransactionDetail(
    val name: String? = "",
    val street: String? = "",
    val city: String? = "",
    val phone: String? = "",
    val transactionId: String? = "",
    val transactionDate: LocalDate?,
    val amount: String? = "",
    val id: String? = ""
)