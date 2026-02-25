package com.dinhodzic.bankingapp

data class ProfileInfo(
    val id: String,
    val avatar: String,
    val first_name: String,
    val last_name: String,
    val full_name: String,
    val email: String
) {
    fun formatAvatarUrl(): String {
        return avatar.replace("\\/", "/")
    }
    companion object {
        val EMPTY = ProfileInfo(id = "", avatar = "", first_name = "", last_name = "", full_name = "", email = "")
    }
}