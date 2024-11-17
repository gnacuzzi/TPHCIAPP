package com.example.peraapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.peraapp.R

//obvio que se puede modularizar mas pero solo si tenemos tiempo
data class HomeBarItem(
    val iconResId: Int,
    val text: String,
    val onClick: () -> Unit
)

val homeItems = listOf(
    HomeBarItem(
        iconResId = R.drawable.transferir,
        text = "Transferir",
        onClick = { /* Acción para "Transferir" */ }
    ),
    HomeBarItem(
        iconResId = R.drawable.ingresar,
        text = "Ingresar",
        onClick = { /* Acción para "Ingresar" */ }
    ),
    HomeBarItem(
        iconResId = R.drawable.cobrar,
        text = "Cobrar",
        onClick = { /* Acción para "Cobrar" */ }
    ),
    HomeBarItem(
        iconResId = R.drawable.invest,
        text = "Invertir",
        onClick = { /* Acción para "Cobrar" */ }
    )
)

@Composable
fun SaldoSection(name: String, saldo: Number) {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet) {
        SaldoSectionTablet(name, saldo)
    } else {
        SaldoSectionPhone(name, saldo)
    }
}

@Composable
fun SaldoSectionPhone(name:String, saldo: Number) {
    Surface (
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(35.dp),
        modifier = Modifier.padding(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "¡Hola, $name!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Tu saldo actual es",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = "$$saldo",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                homeItems.forEach { item ->
                    Button(
                        onClick = item.onClick,
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.secondary
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            val icon: Painter = painterResource(id = item.iconResId)
                            Image(
                                painter = icon,
                                contentDescription = item.text,
                                modifier = Modifier.size(28.dp)
                            )
                            Text(
                                text = item.text,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 4.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SaldoSectionTablet(name:String, saldo: Number) {
    Surface (
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "¡Hola, $name!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Tu saldo actual es",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = "$$saldo",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                homeItems.forEach { item ->
                    Button(
                        onClick = item.onClick,
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.secondary,
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            val icon: Painter = painterResource(id = item.iconResId)
                            Image(
                                painter = icon,
                                contentDescription = item.text,
                                modifier = Modifier.size(28.dp)
                            )
                            Text(
                                text = item.text,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 4.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}