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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.peraapp.R
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.pages.ChargeDialog
import com.example.peraapp.pages.DepositDialog

//obvio que se puede modularizar mas pero solo si tenemos tiempo


val homeItems = listOf(
    AppDestinations.TRANSFERIR,
    AppDestinations.INGRESAR,
    AppDestinations.COBRAR
)

@Composable
fun SaldoSection(
    name: String,
    saldo: Number,
    onNavigateToRoute: (String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet) {
        SaldoSectionTablet(name, saldo, onNavigateToRoute)
    } else {
        SaldoSectionPhone(name, saldo, onNavigateToRoute)
    }
}

@Composable
fun SaldoSectionPhone(
    name:String,
    saldo: Number,
    onNavigateToRoute: (String) -> Unit
) {
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
                text = "${stringResource(R.string.hola)}, $name!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = stringResource(R.string.saldoencuenta),
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
                        onClick = { onNavigateToRoute(item.route) },
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
                                contentDescription = stringResource(item.text),
                                modifier = Modifier.size(28.dp)
                            )
                            Text(
                                text = stringResource(item.text),
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
fun SaldoSectionTablet(
    name: String,
    saldo: Number,
    onNavigateToRoute: (String) -> Unit
) {
    var showCobrarDialog by remember { mutableStateOf(false) }
    var showIngresarDialog by remember { mutableStateOf(false) }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "${stringResource(R.string.hola)}, $name!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = stringResource(R.string.saldoencuenta),
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
                        onClick = {
                            when (item.route) {
                                AppDestinations.COBRAR.route -> showCobrarDialog = true
                                AppDestinations.INGRESAR.route -> showIngresarDialog = true
                                else -> onNavigateToRoute(item.route)
                            }
                        },
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
                                contentDescription = stringResource(item.text),
                                modifier = Modifier.size(28.dp)
                            )
                            Text(
                                text = stringResource(item.text),
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 4.dp),
                            )
                        }
                    }
                }
            }
        }
    }

    if (showCobrarDialog) {
        ChargeDialog(
            onDismissRequest = { showCobrarDialog = false },
            onConfirmation = { /* Lógica de confirmación */ showCobrarDialog = false },
            dialogTitle = stringResource(R.string.cobrar)
        )
    }

    if (showIngresarDialog) {
        DepositDialog(
            onDismissRequest = { showIngresarDialog = false },
            onConfirmation = { /* Lógica de confirmación */ showIngresarDialog = false },
            dialogTitle = stringResource(R.string.ingresar)
        )
    }
}