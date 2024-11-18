package com.example.peraapp.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.R
import com.example.peraapp.components.TopBar
import com.example.peraapp.ui.theme.PeraAppTheme

@Composable
fun DepositPage(onNavigateToRoute: (String) -> Unit){
    Scaffold(
        topBar = { TopBar(R.string.ingresar) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = { onNavigateToRoute("BACK") },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.volveratras),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    text = stringResource(R.string.tucbu),
                    modifier = Modifier.padding(start = 10.dp, bottom = 20.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "00000121213242434354545", //hacerlo responsivo
                    modifier = Modifier
                        .padding(start = 30.dp, bottom = 20.dp)
                        .border(1.dp, MaterialTheme.colorScheme.tertiary)
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = stringResource(R.string.tualias),
                    modifier = Modifier.padding(start = 10.dp, bottom = 20.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "pera.app", //hacerlo responsivo
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .border(1.dp, MaterialTheme.colorScheme.tertiary)
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                Button(//deberia solo aparecer una vez que apretas generar link
                    onClick = { /* Acción para transferir */ },
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
                    Text(stringResource(R.string.compartirdatos), style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DepositPagePreview() {
    PeraAppTheme {
        DepositPage {
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DepositDialogPreview() {
    PeraAppTheme {
        DepositDialog(
            onDismissRequest = { },
            onConfirmation = { },
            dialogTitle = stringResource(R.string.ingresar)
            //habria que pasar el monto
        )
    }
}

@Composable
fun DepositDialog(
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
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
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
                        modifier = Modifier.padding(bottom = 16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                Text(
                    text = stringResource(R.string.tucbu),
                    modifier = Modifier.padding(start = 10.dp, bottom = 20.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "00000121213242434354545", //hacerlo responsivo
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .border(1.dp, MaterialTheme.colorScheme.tertiary)
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = stringResource(R.string.tualias),
                    modifier = Modifier.padding(start = 10.dp, bottom = 20.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "pera.app", //hacerlo responsivo
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.tertiary)
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                Button(//deberia solo aparecer una vez que apretas generar link
                    onClick = { onConfirmation() },
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
                    Text(stringResource(R.string.compartirdatos), style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}
