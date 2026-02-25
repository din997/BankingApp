package com.dinhodzic.bankingapp

import android.util.Log
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dinhodzic.bankingapp.ui.AuthState
import com.dinhodzic.bankingapp.ui.MainViewModel
import com.dinhodzic.bankingapp.ui.login.LoginScreen
import com.dinhodzic.bankingapp.ui.theme.BankingAppTheme

@Composable
fun BankingApp(viewModel: MainViewModel = hiltViewModel()) {
    BankingAppTheme {
        val navController = rememberNavController()
        val profile = viewModel.profileInfo.collectAsStateWithLifecycle()
        Log.e("PROFILEINFO", "profile info ${profile}")
        Surface {
            when(viewModel.authState.collectAsStateWithLifecycle().value){
                AuthState.IDLE -> {}
                AuthState.UNAUTHORIZED ->{
                    LoginScreen()
                }
                AuthState.AUTHORIZED ->{
                    MainScreen(navController = navController, profile.value)
                }
            }
        }
    }
}