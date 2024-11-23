package com.example.peraapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import com.example.peraapp.R
import com.example.peraapp.data.model.Payment
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.pages.MovimientoItem

@Composable
fun MovimientosSection(onNavigateToRoute: (String) -> Unit,
                       payments: List<Payment>,
                       name: String) {
    val configuration = LocalConfiguration.current
    val isTablet = isTablet(configuration)

    if (isTablet) {
        MovimientosSectionTablet(onNavigateToRoute, payments, name)
    } else {
        MovimientosSectionPhone(onNavigateToRoute, payments, name)
    }
}

@Composable
fun MovimientosSectionPhone(onNavigateToRoute: (String) -> Unit,
                            payments: List<Payment>,
                            name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.movimientos),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            TextButton(
                onClick = { onNavigateToRoute(AppDestinations.MOVIMIENTOS.route) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.secondary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    text = stringResource(R.string.vermas),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        LazyColumn {
            items(payments.size) { index ->
                val payment = payments[index]
                var color = Color.Green
                if (payment.receiver.firstName != name) {
                    color = Color.Red
                }
                payment.updatedAt?.let {
                    MovimientoItem(
                        name = payment.receiver.firstName,
                        date = it,
                        amount = payment.amount.toString(),
                        color = color,
                    )
                }
            }
        }
    }
}

@Composable
fun MovimientosSectionTablet(onNavigateToRoute: (String) -> Unit,
                             payments: List<Payment>,
                             name: String) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.movimientos),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
                TextButton(
                    onClick = {onNavigateToRoute(AppDestinations.MOVIMIENTOS.route)},
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        text = stringResource(R.string.vermas),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(payments.size) { index ->
                    val payment = payments[index]
                    var color = Color.Green
                    if (payment.receiver.firstName != name) {
                        color = Color.Red
                    }
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
}
