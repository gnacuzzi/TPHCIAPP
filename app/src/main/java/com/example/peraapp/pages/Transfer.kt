package com.example.peraapp.pages

import androidx.compose.foundation.layout.Arrangement
import com.example.peraapp.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.PreviewSizes
import com.example.peraapp.components.TopBar
import com.example.peraapp.ui.theme.PeraAppTheme
import com.example.peraapp.components.isLandscape
import com.example.peraapp.components.isTablet
import com.example.peraapp.components.BackButton

@Composable
fun TransferPage(onNavigateToRoute: (String) -> Unit){
    val configuration = LocalConfiguration.current
    val isTablet = isTablet(configuration)
    val isLandscape = isLandscape(configuration)

    if (isTablet) {
        if(isLandscape){
            TransferPageTabletLandscape(onNavigateToRoute)
        }else{
            TransferPageTabletPortrait(onNavigateToRoute)
        }
    } else {
        if(isLandscape){
            TransferPagePhoneLandscape(onNavigateToRoute)
        }else{
            TransferPagePhonePortrait(onNavigateToRoute)
        }
    }

}

@Composable
fun TransferPageTabletLandscape(onNavigateToRoute: (String) -> Unit){
    var mail by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(R.string.transferir),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 20.dp).align(Alignment.Start)
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            BackButton(onNavigateToRoute)

            Row (
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                TransferInputField(
                    labelText = stringResource(R.string.mail),
                    value = mail,
                    onValueChange = { mail = it },
                    keyboardType = KeyboardType.Email,
                    width = 200
                )

                TransferInputField(
                    labelText = stringResource(R.string.ingresarmonto),
                    value = amount,
                    onValueChange = { amount = it },
                    keyboardType = KeyboardType.Number,
                    width = 200
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "${stringResource(R.string.formadepago)}:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                )

                LazyRow {//foreach
                    item { CardTablet(name = stringResource(R.string.saldoencuenta), bank = "Pera", number = "0", date = "") { } }
                    item { CardTablet(name = "Samanta Jones", bank = "Santander", number = "1234 1111 5678 2212", date = "12/28") { } }
                }

                TransferButton {  }
            }
        }
    }
}

@Composable
fun TransferPageTabletPortrait(onNavigateToRoute: (String) -> Unit){
    var mail by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(R.string.transferir),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 20.dp).align(Alignment.Start)
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            BackButton(onNavigateToRoute)

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                TransferInputField(
                    labelText = stringResource(R.string.mail),
                    value = mail,
                    onValueChange = { mail = it },
                    keyboardType = KeyboardType.Email,
                    width = 200
                )

                TransferInputField(
                    labelText = stringResource(R.string.ingresarmonto),
                    value = amount,
                    onValueChange = { amount = it },
                    keyboardType = KeyboardType.Number,
                    width = 200
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp)
            ){
                Text(
                    text = "${stringResource(R.string.formadepago)}:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                )

                LazyRow {//foreach
                    item { CardTablet(name = stringResource(R.string.saldoencuenta), bank = "Pera", number = "0", date = "") { } }
                    item { CardTablet(name = "Samanta Jones", bank = "Santander", number = "1234 1111 5678 2212", date = "12/28") { } }
                }

                TransferButton {  }
            }
        }
    }
}

@Composable
fun TransferPagePhoneLandscape(onNavigateToRoute: (String) -> Unit){
    var mail by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopBar(R.string.transferir, false) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row (
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ){
                BackButton(onNavigateToRoute)
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TransferInputField(
                        labelText = stringResource(R.string.mail),
                        value = mail,
                        onValueChange = { mail = it },
                        keyboardType = KeyboardType.Email,
                        width = 200
                    )

                    TransferInputField(
                        labelText = stringResource(R.string.ingresarmonto),
                        value = amount,
                        onValueChange = { amount = it },
                        keyboardType = KeyboardType.Number,
                        width = 200
                    )
                }
                LazyColumn{//foreach
                    item {
                        CardHome(
                            name = stringResource(R.string.saldoencuenta),
                            bank = "Pera",
                            number = "0",
                            date = ""
                        ) { }
                    }
                    item {
                        CardHome(
                            name = "Samanta Jones",
                            bank = "Santander",
                            number = "1234 1111 5678 2212",
                            date = "12/28"
                        ) { }
                    }
                }
                TransferButton(
                    onClick = { /* Acción para transferir */ }
                )
            }
        }
    }
}
@Composable
fun TransferPagePhonePortrait(onNavigateToRoute: (String) -> Unit){
    var mail by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopBar(R.string.transferir) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BackButton(onNavigateToRoute)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TransferInputField(
                    labelText = stringResource(R.string.mail),
                    value = mail,
                    onValueChange = { mail = it },
                    keyboardType = KeyboardType.Email
                )

                TransferInputField(
                    labelText = stringResource(R.string.ingresarmonto),
                    value = amount,
                    onValueChange = { amount = it },
                    keyboardType = KeyboardType.Number
                )

                LazyRow{//foreach
                    item { Card(name = stringResource(R.string.saldoencuenta), bank = "Pera", number = "0", date = "") { } }
                    item { Card(name = "Samanta Jones", bank = "Santander", number = "1234 1111 5678 2212", date = "12/28") { } }
                }


                TransferButton(
                    onClick = { /* Acción para transferir */ }
                )
            }
        }
    }
}


@Composable
fun TransferInputField(
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    width: Int = 270
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(top = 8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(labelText) },
            modifier = Modifier
                .padding(bottom = 10.dp, top = 20.dp)
                .width(width.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType
            ),
        )
    }
}


@Composable
fun TransferButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(top = 20.dp)
            .width(200.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(stringResource(R.string.transferir), style = MaterialTheme.typography.titleLarge)
    }
}


@PreviewSizes
@Composable
fun TransferPagePreview() {
    PeraAppTheme{
        TransferPage{}
    }
}


@Preview(showBackground = true)
@Composable
fun TransferDialogPreview() {
    PeraAppTheme {
        TransferDialog(
            onDismissRequest = { },
            onConfirmation = { },
            dialogTitle = "${stringResource(R.string.deseatransaccion)}?"
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
                Text("${stringResource(R.string.pagoa)}: Jane Doe")
                Text("${stringResource(R.string.monto)}: $200")
                Text("${stringResource(R.string.con)}: Saldo en cuenta")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(
                            text = stringResource(R.string.cancelar),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    TextButton(onClick = { onConfirmation() }) {
                        Text(
                            text = stringResource(R.string.confirmar),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}
