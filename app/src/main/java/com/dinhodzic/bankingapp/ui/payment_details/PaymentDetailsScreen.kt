package com.dinhodzic.bankingapp.ui.payment_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dinhodzic.bankingapp.R
import com.dinhodzic.bankingapp.domain.model.TransactionDetail
import com.dinhodzic.bankingapp.ui.common.components.DashedDivider
import com.dinhodzic.bankingapp.ui.common.components.StandardCircularProgressBar
import com.dinhodzic.bankingapp.ui.theme.Typography
import java.time.format.DateTimeFormatter

@Composable
fun PaymentDetailsScreen(
    viewModel: PaymentDetailsViewModel = hiltViewModel()
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()


    when (uiState.value) {

        is TransactionDetailsUiState.Loading -> {
            LoadingScreen()
        }

        is TransactionDetailsUiState.Success -> {
            val transaction = (uiState.value as TransactionDetailsUiState.Success).transaction
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 25.dp)
                    .padding(bottom = 20.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                PaymentDetailsContent(transaction)
            }
        }

        is TransactionDetailsUiState.Error -> {}
    }

}

@Composable
fun PaymentDetailsContent(details: TransactionDetail) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {

        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                KeyValueRow(stringResource(R.string.name), details.name ?: "")
                KeyValueRow(stringResource(R.string.address), "${details.street} ${details.city}")
                KeyValueRow(stringResource(R.string.phone_number), details.phone ?: "")
                KeyValueRow(stringResource(R.string.code), details.transactionId ?: "")
                KeyValueRow(
                    stringResource(R.string.date),
                    details.transactionDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
                )

                Spacer(Modifier.height(20.dp))

                DashedDivider(thickness = 2.dp)
                Spacer(Modifier.height(16.dp))

                TotalRow("$${details.amount}")
            }
        }
    }

}

@Composable
fun LoadingScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        StandardCircularProgressBar()
    }
}

@Composable
fun TotalRow(
    total: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "TOTAL",
            style = Typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = total,
            style = Typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE83F5B)
        )
    }
}

@Composable
fun KeyValueRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Visible,
            fontWeight = FontWeight.Medium
        )
    }
}
