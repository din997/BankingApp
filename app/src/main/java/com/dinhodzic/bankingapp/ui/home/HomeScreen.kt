package com.dinhodzic.bankingapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dinhodzic.bankingapp.R
import com.dinhodzic.bankingapp.domain.model.BankCard
import com.dinhodzic.bankingapp.ui.common.util.maskIban
import com.dinhodzic.bankingapp.ui.theme.Typography
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onActionClicked: () -> Unit
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
    ) {
        when (state.value) {
            is HomeScreenUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.padding(24.dp)
                )
            }

            is HomeScreenUiState.Success -> {
                VerticalCardCarousel((state.value as HomeScreenUiState.Success).creditCards)
            }

            is HomeScreenUiState.Empty -> {

            }
        }
        ActionGridSection {
            onActionClicked.invoke()
        }
    }
}

@Composable
fun VerticalCardCarousel(
    cards: List<BankCard>,
    modifier: Modifier = Modifier
) {
    if (cards.isEmpty()) return

    val pagerState = rememberPagerState(
        pageCount = { cards.size }
    )

    VerticalPager(
        state = pagerState,
        pageSpacing = (-40).dp,
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
    ) { page ->

        val pageOffset = (
                (pagerState.currentPage - page) +
                        pagerState.currentPageOffsetFraction
                ).absoluteValue

        val scale = lerp(
            start = 0.9f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )

        val translationY = lerp(
            start = 60f,
            stop = 0f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )

        BankCardItem(
            card = cards[page],
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.translationY = translationY
                }
                .zIndex(1f - pageOffset)
        )
    }
}

@Composable
fun BankCardItem(
    card: BankCard,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_card_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 25.dp)

            ) {
                Text(
                    text = card.clientName ?: "",
                    style = Typography.bodyLarge,
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = card.name ?: "",
                    style = Typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.85f)
                )

                Text(
                    text = card.iban?.maskIban() ?: "",
                    style = Typography.bodyMedium,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "${card.currency} ${card.available}",
                        style = Typography.headlineMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ActionGridSection(onActionClicked: () -> Unit) {
    val actions = listOf(
        ActionItemData(
            stringResource(R.string.account_and_card),
            R.drawable.ic_wallet,
            Color(0xFF5E35B1),
            {}),
        ActionItemData(
            stringResource(R.string.transfer),
            R.drawable.ic_transfer,
            Color(0xFFE57373),
            {}),
        ActionItemData(
            stringResource(R.string.withdraw),
            R.drawable.ic_withdraw,
            Color(0xFF1E88E5),
            {}),
        ActionItemData(
            stringResource(R.string.mobile_prepaid),
            R.drawable.ic_mobile_prepaid,
            Color(0xFFFB8C00),
            {}),
        ActionItemData(
            stringResource(R.string.pay_the_bill),
            R.drawable.ic_pay_the_bill,
            Color(0xFF26A69A),
            {}),
        ActionItemData(
            stringResource(R.string.save_online),
            R.drawable.ic_save_online,
            Color(0xFF7E57C2),
            {}),
        ActionItemData(
            stringResource(R.string.credit_card),
            R.drawable.ic_credit_card,
            Color(0xFFFF7043),
            {}),
        ActionItemData(
            stringResource(R.string.transaction_report),
            R.drawable.ic_transaction_report,
            Color(0xFF3F51B5),
            {
                onActionClicked.invoke()
            }),
        ActionItemData("Beneficiary", R.drawable.ic_beneficiary, Color(0xFFEC407A), {})
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(actions) { action ->
            ActionTile(action)
        }
    }
}

@Composable
fun ActionTile(action: ActionItemData) {
    Surface(
        onClick = { action.onActionClicked.invoke() },
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        modifier = Modifier.aspectRatio(1f)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = action.iconRes),
                contentDescription = null,
                tint = action.tintColor,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = action.label,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 11.sp,
                    lineHeight = 14.sp,
                    fontWeight = FontWeight.Medium
                ),
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}

data class ActionItemData(
    val label: String,
    val iconRes: Int,
    val tintColor: Color,
    val onActionClicked: () -> Unit?
)