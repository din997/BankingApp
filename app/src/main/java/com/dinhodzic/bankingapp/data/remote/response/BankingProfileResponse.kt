package com.dinhodzic.bankingapp.data.remote.response

data class BankingProfileResponse(
    val id: Int,
    val name: String,
    val avatar: String,
    val country: String,
    val street: String
)