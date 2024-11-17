package com.example.peraapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.peraapp.pages.CardHome
import com.example.peraapp.pages.CardHomeTablet

@Composable
fun TarjetasSection() {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet) {
        TarjetasSectionTablet()
    } else {
        TarjetasSectionPhone()
    }
}

@Composable
fun TarjetasSectionPhone() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Tarjetas",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            CardHome(
                bank = "Santander",
                number = "1234 5678 9101 1121",
                name = "Samanta Jones",
                date = "12/28"
            ){}
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Agregar tarjeta",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(70.dp)
                )
            }
        }
    }
}

@Composable
fun TarjetasSectionTablet() {
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
            Text(
                text = "Tarjetas",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CardHomeTablet(
                    bank = "Santander",
                    number = "1234 5678 9101 1121",
                    name = "Samanta Jones",
                    date = "12/28"
                ){}
                CardHomeTablet(
                    bank = "BBVA",
                    number = "4321 8765 1011 2233",
                    name = "Juan Pérez",
                    date = "01/25"
                ){}
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(65.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Agregar tarjeta",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(65.dp)
                    )
                }
            }
        }
    }
}
