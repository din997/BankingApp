package com.dinhodzic.bankingapp.ui.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dinhodzic.bankingapp.ui.common.components.CategoryFilterRow
import com.dinhodzic.bankingapp.ui.common.components.TransactionItem

@Composable
fun PaymentHistoryScreen(
    viewModel: PaymentHistoryViewModel = hiltViewModel(),
    onTransactionClicked: (transactionId: String) -> Unit
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
    ) {

        CategoryFilterRow(
            selected = uiState.value.selectedCategory,
            onSelected = viewModel::onCategorySelected
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(uiState.value.transactions) { transaction ->
                TransactionItem(transaction) {
                    onTransactionClicked.invoke(transaction.id ?: "0")
                }
            }
        }
    }
}
