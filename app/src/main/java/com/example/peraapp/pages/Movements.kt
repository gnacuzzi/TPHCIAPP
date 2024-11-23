package com.example.peraapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.peraapp.HomeViewModel
import com.example.peraapp.R
import com.example.peraapp.components.ModularizedLayout
import com.example.peraapp.components.TopBar
import com.example.peraapp.data.model.Payment

@Composable
fun MovementsScreen(
    viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val payments = uiState.payments ?: emptyList()
    val isNextEnabled by viewModel.hasMorePayments.collectAsState()
    val isPreviousEnabled = viewModel.currentPage > 1

    var name = stringResource(R.string.desconocido)
    if (uiState.currentUser != null) {
        name = uiState.currentUser!!.firstName
    }

    ModularizedLayout(
        contentPhonePortrait = {
            MovementsScreenPhone(
                isLanscape = false,
                payments = payments,
                name = name,
                onNextPage = { viewModel.nextPage() },
                onPreviousPage = { viewModel.previousPage() },
                isPreviousEnabled = isPreviousEnabled,
                isNextEnabled = isNextEnabled
            )
        },
        contentPhoneLandscape = {
            MovementsScreenPhone(
                isLanscape = true,
                payments = payments,
                name = name,
                onNextPage = { viewModel.nextPage() },
                onPreviousPage = { viewModel.previousPage() },
                isPreviousEnabled = isPreviousEnabled,
                isNextEnabled = isNextEnabled
            )
        },
        contentTabletPortrait = {
            MovementsScreenTablet(
                payments = payments,
                name = name,
                onNextPage = { viewModel.nextPage() },
                onPreviousPage = { viewModel.previousPage() },
                isPreviousEnabled = isPreviousEnabled,
                isNextEnabled = isNextEnabled
            )
        },
        contentTabletLandscape = {
            MovementsScreenTablet(
                payments = payments,
                name = name,
                onNextPage = { viewModel.nextPage() },
                onPreviousPage = { viewModel.previousPage() },
                isPreviousEnabled = isPreviousEnabled,
                isNextEnabled = isNextEnabled
            )
        }
    )

}

@Composable
fun MovementsScreenPhone(
    isLanscape: Boolean,
    payments: List<Payment>,
    name: String,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    isPreviousEnabled: Boolean,
    isNextEnabled: Boolean
) {
    Column(
    ) {
        TopBar(R.string.movimientos, !isLanscape)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onPreviousPage,
                enabled = isPreviousEnabled
            ) {
                Text(text = stringResource(R.string.previa))
            }
            Button(
                onClick = onNextPage,
                enabled = isNextEnabled
            ) {
                Text(text = stringResource(R.string.proxima))
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                )
        ) {
            items(payments.size) { index ->
                val payment = payments[index]
                val color = if (payment.receiver.firstName == name) Color.Green else Color.Red
                payment.updatedAt?.let {
                    MovimientoItem(
                        name = payment.receiver.firstName,
                        date = it,
                        amount = payment.amount.toString(),
                        color = color
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}


@Composable
fun MovementsScreenTablet(
    payments: List<Payment>,
    name: String,
    onNextPage: () -> Unit,
    onPreviousPage: () -> Unit,
    isPreviousEnabled: Boolean,
    isNextEnabled: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(R.string.movimientos),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .align(Alignment.Start)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onPreviousPage,
                enabled = isPreviousEnabled
            ) {
                Text(text = stringResource(R.string.previa))
            }
            Button(
                onClick = onNextPage,
                enabled = isNextEnabled
            ) {
                Text(text = stringResource(R.string.proxima))
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(payments.size) { index ->
                val payment = payments[index]
                val color = if (payment.receiver.firstName == name) Color.Green else Color.Red
                payment.updatedAt?.let {
                    MovimientoItem(
                        name = payment.receiver.firstName,
                        date = it,
                        amount = payment.amount.toString(),
                        color = color,
                        iconSize = 70
                    )
                }
            }
        }
    }
}


@Composable
fun MovimientoItem(name: String, date: String, amount: String, color: Color, iconSize: Int = 50) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            LetterIcon(letter = name.first(), iconSize)
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
        var sign = "+"
        if (color == Color.Red){
            sign = "-"
        }
        Text(
            text = "$sign$$amount",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}


@Composable
fun LetterIcon(
    letter: Char,
    size: Int,
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.background)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

