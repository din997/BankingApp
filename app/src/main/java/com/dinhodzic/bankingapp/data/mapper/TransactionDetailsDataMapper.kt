package com.dinhodzic.bankingapp.data.mapper

import com.dinhodzic.bankingapp.data.remote.response.TransactionDetailsResponse
import com.dinhodzic.bankingapp.domain.model.TransactionDetail
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun TransactionDetailsResponse.toDomain(): TransactionDetail {
    val date = if (!createdAt.isNullOrEmpty()) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        LocalDate.parse(transactionDate, formatter)
    } else {
        null
    }

    return TransactionDetail(
        id = id,
        name = name,
        street = street,
        city = city,
        phone = phone,
        transactionId = transactionId,
        transactionDate = date,
        amount = amount,
    )
}