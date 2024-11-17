package com.example.peraapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.peraapp.R

data class BottomBarItem(
    val iconResId: Int,
    val text: String,
    val onClick: () -> Unit
)

val bottomBarItems = listOf(
    BottomBarItem(
        iconResId = R.drawable.home,
        text = "Inicio",
        onClick = { /* Acción para "Inicio" */ }
    ),
    BottomBarItem(
        iconResId = R.drawable.movimientos,
        text = "Movimientos",
        onClick = { /* Acción para "Movimientos" */ }
    ),
    BottomBarItem(
        iconResId = R.drawable.tarjetas,
        text = "Tarjetas",
        onClick = { /* Acción para "Tarjetas" */ }
    ),
    BottomBarItem(
        iconResId = R.drawable.cuenta,
        text = "Cuenta",
        onClick = { /* Acción para "Cuenta" */ }
    )
)

@Composable
fun BottomBar(
    //onCenterButtonClick: () -> Unit //descomentar si se agrega lo del qr
) {
    Box {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.tertiary,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                bottomBarItems.forEach { item ->
                    Button(
                        onClick = item.onClick,
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            val icon: Painter = painterResource(id = item.iconResId)
                            Image(
                                painter = icon,
                                contentDescription = item.text,
                                modifier = Modifier.size(32.dp)
                            )
                            Text(
                                text = item.text,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
        /* es opcional, para mi saquemoslo
        FloatingActionButton(
            onClick = onCenterButtonClick,
            containerColor = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-35).dp)
                .size(75.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(R.drawable.qrpera),
                contentDescription = "QR Code",
                modifier = Modifier.size(50.dp)
            )
        }

         */
    }
}