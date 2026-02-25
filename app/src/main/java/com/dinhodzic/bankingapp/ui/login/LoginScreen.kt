package com.dinhodzic.bankingapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dinhodzic.bankingapp.BankingBottomBar
import com.dinhodzic.bankingapp.MainTopBar
import com.dinhodzic.bankingapp.NotificationBell
import com.dinhodzic.bankingapp.R
import com.dinhodzic.bankingapp.StandardTopAppBar
import com.dinhodzic.bankingapp.data.remote.performGoogleSignIn
import com.dinhodzic.bankingapp.ui.common.components.BackIconButton
import com.dinhodzic.bankingapp.ui.theme.Primary900
import com.dinhodzic.bankingapp.ui.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = Primary900,
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Text(
                            "Sign in",
                            color = Color.White,
                            style = Typography.titleLarge
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White
                ),
                scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            )
        }

    ) { innerPadding ->
        innerPadding.calculateTopPadding()
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize()
                .background(
                    color = Color(0xFFFBFBFC),
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                )
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(top = 25.dp, start = 25.dp)
            ) {
                Text(
                    text = stringResource(R.string.welcome_back),
                    style = Typography.headlineMedium,
                    color = Primary900
                )

                Text(
                    text = stringResource(R.string.helo_there),
                    style = Typography.bodyMedium
                )
            }
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 25.dp),
                painter = painterResource(R.drawable.ic_login_ilustration),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {
                    scope.launch {
                        val email = performGoogleSignIn(context)
                        viewModel.onLoginSuccess(email)
                    }
                },
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .height(56.dp),
                colors = ButtonColors(
                    containerColor = Primary900,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.LightGray
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.sign_in_with_google))
                }
            }
        }

    }
}