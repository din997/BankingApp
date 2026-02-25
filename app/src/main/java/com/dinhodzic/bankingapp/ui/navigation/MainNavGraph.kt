package com.dinhodzic.bankingapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dinhodzic.bankingapp.ui.home.HomeScreen
import com.dinhodzic.bankingapp.ui.payment.PaymentHistoryScreen
import com.dinhodzic.bankingapp.ui.payment_details.PaymentDetailsScreen

internal const val transactionId: String = "transactionId"

@Composable
fun MainNavGraph(
    navController: NavHostController,
    startDestination: String = MainRoutes.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier
    ) {
        composable(MainRoutes.Home.route) {
            HomeScreen() {
                navController.navigate(MainRoutes.TransactionListScreen.route)
            }
        }

        composable(MainRoutes.Settings.route) {
            // todo add screen when needed
        }

        composable(MainRoutes.Search.route) {
            // todo add screen when needed
        }

        composable(MainRoutes.Messages.route) {
            // todo add Screen when needed
        }

        composable(MainRoutes.TransactionListScreen.route) {
            PaymentHistoryScreen() { transactionId ->
                navController.navigate(MainRoutes.TransactionDetailsScreen.route + "/${transactionId}")

            }
        }

        composable(MainRoutes.TransactionDetailsScreen.route + "/{$transactionId}", arguments = listOf(
            navArgument(transactionId) { type = NavType.StringType }
        )) {
            PaymentDetailsScreen()
        }
    }
}