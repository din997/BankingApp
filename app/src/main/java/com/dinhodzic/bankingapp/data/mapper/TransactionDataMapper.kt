package com.dinhodzic.bankingapp.data.mapper

import com.dinhodzic.bankingapp.data.remote.response.TransactionResponse
import com.dinhodzic.bankingapp.domain.model.Transaction
import com.dinhodzic.bankingapp.domain.model.TransactionCategory
import com.dinhodzic.bankingapp.domain.model.TransactionStatus
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun TransactionResponse.toDomain(): Transaction {
    val date = if (!createdAt.isNullOrEmpty()) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        LocalDate.parse(createdAt, formatter)
    } else {
        null
    }
    return Transaction(
        id = id,
        title = title,
        date = date,
        amount = amount,
        status = status?.toTransactionStatus(),
        category = category?.toTransactionCategory(),
        description = description,
        currency = currency
    )
}

private fun String.toTransactionStatus(): TransactionStatus {
    return when (this.uppercase()) {
        "SUCCESS" -> TransactionStatus.SUCCESS
        else -> TransactionStatus.FAILED
    }
}

private fun String.toTransactionCategory(): TransactionCategory {
    return when (this.uppercase()) {
        "ELECTRIC" -> TransactionCategory.ELECTRIC
        "WATER" -> TransactionCategory.WATER
        "MOBILE" -> TransactionCategory.MOBILE
        else -> TransactionCategory.ELECTRIC
    }
}