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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.peraapp.R
import com.example.peraapp.navigation.AppDestinations

//obvio que se puede modularizar mas pero solo si tenemos tiempo


val homeItems = listOf(
    AppDestinations.TRANSFERIR,
    AppDestinations.INGRESAR,
)

@Composable
fun SaldoSection(
    name: String,
    saldo: Double,
    onNavigateToRoute: (String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val isTabletDevice = isTablet(configuration)
    val isLandscape = isLandscape(configuration)

    if ((isTabletDevice && isLandscape) || (isTabletDevice)) {
        SaldoSectionTablet(name, saldo, onNavigateToRoute)
    } else {
        SaldoSectionPhone(name, saldo, onNavigateToRoute)
    }
}

@Composable
fun SaldoSectionPhone(
    name:String,
    saldo: Double,
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
                            Image(
                                imageVector = item.icon,
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
    saldo: Double,
    onNavigateToRoute: (String) -> Unit
) {
    var showCobrarDialog by remember { mutableStateOf(false) }

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
                        onClick = { onNavigateToRoute(item.route) },
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
                            Image(
                                imageVector = item.icon,
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