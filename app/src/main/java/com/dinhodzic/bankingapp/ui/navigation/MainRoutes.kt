package com.dinhodzic.bankingapp.ui.navigation

sealed class MainRoutes(val route: String)  {
    object Home: MainRoutes("home")
    object Search: MainRoutes("search")
    object Messages: MainRoutes("message")
    object Settings: MainRoutes("settings")
    object TransactionListScreen: MainRoutes("transaction_list")
    object TransactionDetailsScreen: MainRoutes("payment_details")

}