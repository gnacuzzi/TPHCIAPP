package com.example.peraapp.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.R
import com.example.peraapp.components.TopBar
import com.example.peraapp.components.isLandscape
import com.example.peraapp.ui.theme.PeraAppTheme
import com.example.peraapp.components.BackButton

@Composable
fun ChargePage(onNavigateToRoute: (String) -> Unit) {
    val configuration = LocalConfiguration.current
    val isLandscape = isLandscape(configuration)

    if (isLandscape) {
        LandscapeChargeContent(
            onNavigateToRoute = onNavigateToRoute,
        )
    } else {
        PortraitChargeContent(
            onNavigateToRoute = onNavigateToRoute,
        )
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LandscapeChargeContent(
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopBar(R.string.cobrar, false) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            BackButton(onNavigateToRoute)
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                BackButton(onNavigateToRoute)
                AmountInputSection(onNavigateToRoute)
                LinkGeneratedSection()
            }
        }
    }

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PortraitChargeContent(
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopBar(R.string.cobrar) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            BackButton(onNavigateToRoute)
            Column (
                modifier = modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AmountInputSection(onNavigateToRoute)
                LinkGeneratedSection()
            }

        }
    }
}


@Composable
fun AmountInputSection(onNavigateToRoute: (String) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "${stringResource(R.string.ingresarmonto)}:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = { Text(stringResource(R.string.monto)) },
            modifier = Modifier
                .padding(bottom = 10.dp, top = 20.dp)
                .align(Alignment.CenterHorizontally),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            textStyle = MaterialTheme.typography.titleLarge
        )

        Button(
            onClick = { /* Acción para generar link */ },
            modifier = Modifier
                .padding(top = 60.dp)
                .width(270.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.secondary,
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(stringResource(R.string.generarlink), style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun LinkGeneratedSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "${stringResource(R.string.linkgenerado)}: https:/aparece..cuando..apretas",
            modifier = Modifier.padding(top = 20.dp).align(Alignment.CenterHorizontally)
        )

        Button(
            onClick = { /* Acción para compartir */ },
            modifier = Modifier
                .padding(top = 60.dp)
                .width(270.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.secondary,
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(stringResource(R.string.compartir), style = MaterialTheme.typography.titleMedium)
        }
    }
}




@Preview(device = "spec:width=411dp,height=891dp")
@Composable
fun ChargePagePortraitPreview() {
    PeraAppTheme {
        ChargePage{

        }
    }
}
@Preview(device = "spec:width=891dp,height=411dp")
@Composable
fun ChargePageLandscapePreview() {
    PeraAppTheme {
        ChargePage{

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChargeDialogPreview() {
    PeraAppTheme {
        ChargeDialog(
            onDismissRequest = { },
            onConfirmation = { },
            dialogTitle = stringResource(R.string.cobrar)
            //habria que pasar el monto
        )
    }
}

@Composable
fun ChargeDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            modifier = Modifier.width(800.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    IconButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.volveratras),
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    Text(
                        text = dialogTitle,
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterVertically)
                    )
                }
                Text(
                    text = "${stringResource(R.string.ingresarmonto)}:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text(stringResource(R.string.monto)) },
                    modifier = Modifier
                        .padding(bottom = 10.dp, top = 20.dp)
                        .align(Alignment.CenterHorizontally),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    textStyle = MaterialTheme.typography.titleLarge
                )

                Button(
                    onClick = { /* Acción para transferir */ },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .width(270.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(stringResource(R.string.generarlink), style = MaterialTheme.typography.titleLarge)
                }

                Text(
                    text = "${stringResource(R.string.linkgenerado)}: https:/aparece..cuando..apretas",
                    modifier = Modifier.padding(top = 20.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                Button(//deberia solo aparecer una vez que apretas generar link
                    onClick = { onConfirmation() },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .width(270.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(stringResource(R.string.compartir), style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}