package com.dinhodzic.bankingapp.domain.model

import com.dinhodzic.bankingapp.ProfileInfo

data class BankingProfile(
    val id: Int,
    val name: String,
    val avatar: String,
    val country: String,
    val street: String
) {
    companion object {
        val EMPTY = BankingProfile(id = 0, avatar = "", name = "", country = "", street = "")
    }
}
