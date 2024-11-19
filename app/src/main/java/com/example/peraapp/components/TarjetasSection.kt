package com.example.peraapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.peraapp.R
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.pages.AddCardTabletDialog
import com.example.peraapp.pages.CardHome
import com.example.peraapp.pages.CardHomeTablet

@Composable
fun TarjetasSection(
    onNavigateToRoute: (String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val isTablet = isTablet(configuration)

    if (isTablet) {
        TarjetasSectionTablet(onNavigateToRoute)
    } else {
        TarjetasSectionPhone(onNavigateToRoute)
    }
}

@Composable
fun TarjetasSectionPhone(
    onNavigateToRoute: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.tarjetas),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                CardHome(
                    bank = "Santander",
                    number = "1234 5678 9101 1121",
                    name = "Samanta Jones",
                    date = "12/28"
                ){onNavigateToRoute(AppDestinations.ELIMINARTARJETA.route)}
            }
            item {
                IconButton(
                    onClick = {onNavigateToRoute(AppDestinations.AGREGARTARJETA.route)}
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = stringResource(R.string.agregartarjeta),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(70.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TarjetasSectionTablet(
    onNavigateToRoute: (String) -> Unit
) {
    var showAddCardDialog by remember { mutableStateOf(false) }
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
                text = stringResource(R.string.tarjetas),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    CardHomeTablet(
                        bank = "Santander",
                        number = "1234 5678 9101 1121",
                        name = "Samanta Jones",
                        date = "12/28"
                    ){}
                }
                item {
                    CardHomeTablet(
                        bank = "BBVA",
                        number = "4321 8765 1011 2233",
                        name = "Juan Pérez",
                        date = "01/25"
                    ){onNavigateToRoute(AppDestinations.ELIMINARTARJETA.route)}
                }
                item {
                    IconButton(
                        onClick = { showAddCardDialog = true},
                        modifier = Modifier.size(65.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = stringResource(R.string.agregartarjeta),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(65.dp)
                        )
                    }
                }
            }
        }
    }
    if (showAddCardDialog) {
        AddCardTabletDialog(
            onDismissRequest = { showAddCardDialog = false },
            onConfirmation = {
                showAddCardDialog = false
                // Agrega la lógica para confirmar
            }
        )
    }
}
