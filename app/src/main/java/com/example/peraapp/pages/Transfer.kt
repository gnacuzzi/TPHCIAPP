package com.example.peraapp.pages

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.PreviewSizes
import com.example.peraapp.components.TopBar
import com.example.peraapp.components.TopBarTablet
import com.example.peraapp.ui.theme.PeraAppTheme

class Transfer {
}

@Composable
fun TransferPage(name: String = "",bodycontent: @Composable () -> Unit){
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if(isTablet){
        Scaffold(
            topBar = { TopBarTablet(name) },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                bodycontent()
            }
        }
    } else{
        Scaffold(
            topBar = { TopBar("Transferir") }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                bodycontent()
            }
        }
    }

}

@Composable
fun TransferContent() {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet) {
        TransferContentTablet()
    } else {
        TransferContentPhone()
    }
}

@Composable
fun TransferContentPhone() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = { /* Aquí iría la acción para volver atrás */ },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver atrás",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = { Text("Mail") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        )

        Text(
            text = "Ingresa el monto:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = { Text("Monto") },
            modifier = Modifier
                .padding(bottom = 10.dp, top = 20.dp)
                .align(Alignment.CenterHorizontally),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            textStyle = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Forma de pago:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Row (
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ){//foreach
            Card(name = "Saldo en cuenta", bank = "Pera", number = "0", date = "") { }
            Card(name = "Samanta Jones", bank = "Santander", number = "1234 1111 5678 2212", date = "12/28") { }
        }

        Button(
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
            Text("Transferir", style = MaterialTheme.typography.titleMedium)
        }

    }
}


@Composable
fun TransferContentTablet() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        IconButton(
            onClick = { /* Aquí iría la acción para volver atrás */ },
            modifier = Modifier.padding(bottom = 16.dp, start = 20.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver atrás",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(100.dp)
            )
        }

        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Mail",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text("Mail") },
                    modifier = Modifier.padding(bottom = 10.dp, top = 20.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    )
                )
            }

            Column {
                Text(
                    text = "Ingresa el monto:",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text("Monto") },
                    modifier = Modifier
                        .padding(bottom = 10.dp, top = 20.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    textStyle = MaterialTheme.typography.titleLarge
                )
            }
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "Forma de pago:",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            )

            Row (
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ){//foreach
                CardTablet(name = "Saldo en cuenta", bank = "Pera", number = "0", date = "") { }
                CardTablet(name = "Samanta Jones", bank = "Santander", number = "1234 1111 5678 2212", date = "12/28") { }

            }

            Button(
                onClick = { /* Acción para transferir */ },
                modifier = Modifier
                    .padding(top = 50.dp)
                    .width(270.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.secondary,
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Transferir", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

@PreviewSizes
@Composable
fun TransferPagePreview() {
    PeraAppTheme{
        TransferPage("Transferir"){
            TransferContent()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TransferDialogPreview() {
    PeraAppTheme {
        TransferDialog(
            onDismissRequest = { },
            onConfirmation = { },
            dialogTitle = "¿Desea realizar esta transacción?"
            //habria que pasar la tarjeta pero actualmente es una funcion no una clase
        )
    }
}

@Composable
fun TransferDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = dialogTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                //habria que hacer una transaccion y mandarla como parametro
                Text("Pago a: Jane Doe")
                Text("Monto: $200")
                Text("Con: Saldo en cuenta")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(
                            text = "Cancelar",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    TextButton(onClick = { onConfirmation() }) {
                        Text(
                            text = "Confirmar",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}
