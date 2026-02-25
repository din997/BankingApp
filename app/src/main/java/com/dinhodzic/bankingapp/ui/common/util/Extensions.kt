package com.dinhodzic.bankingapp.ui.common.util

fun String.maskIban(): String {
    return this.replace("^(.{4})(.*)(.{4})$".toRegex()) {
        "${it.groupValues[1]} •••• •••• ${it.groupValues[3]}"
    }
}