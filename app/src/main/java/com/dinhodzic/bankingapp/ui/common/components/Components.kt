package com.dinhodzic.bankingapp.ui.common.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dinhodzic.bankingapp.R
import com.dinhodzic.bankingapp.domain.model.Transaction
import com.dinhodzic.bankingapp.domain.model.TransactionCategory
import com.dinhodzic.bankingapp.ui.theme.Typography
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun BackIconButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier.padding(top = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack, contentDescription = ""
        )
    }
}

@Composable
fun TransactionItem(transaction: Transaction, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = transaction.date?.month?.name?.lowercase()
                        ?.replaceFirstChar { it.uppercase() } ?: "",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = transaction.date?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = transaction.description ?: stringResource(R.string.no_description),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = Typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "${transaction.currency}${transaction.amount}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun CategoryFilterRow(
    selected: TransactionCategory,
    onSelected: (TransactionCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TransactionCategory.entries.forEach { category ->

            CategoryChip(
                text = category.name.lowercase().capitalize(),
                selected = category == selected,
                onClick = { onSelected(category) }
            )
        }
    }
}

@Composable
private fun CategoryChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor =
        if (selected) Color(0xFF3F2DBE)
        else Color(0xFFE9E9EF)

    val textColor =
        if (selected) Color.White
        else Color(0xFF2D2D2D)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 28.dp, vertical = 14.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            style = Typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun StandardCircularProgressBar(size: Dp = 40.dp, strokeWidth: Dp = 3.dp) {
    CircularProgressIndicator(
        strokeWidth = strokeWidth,
        modifier = Modifier.size(size)
    )
}

@Composable
fun DashedDivider(
    thickness: Dp = 1.dp,
    color: Color = Color.LightGray
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(thickness)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = thickness.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )
    }
}