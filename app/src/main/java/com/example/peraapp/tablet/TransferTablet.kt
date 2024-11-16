package com.example.peraapp.tablet

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peraapp.BottomBar
import com.example.peraapp.Card
import com.example.peraapp.R
import com.example.peraapp.TopBar
import com.example.peraapp.TransferContent
import com.example.peraapp.ui.theme.PeraAppTheme

class TransferTablet {
}

@Composable
fun TransferPageTablet(name: String,
                       bodyContent: @Composable () -> Unit) {
    Scaffold(
        topBar = { TopBarTablet(name) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            bodyContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarTablet(name: String) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.secondary,
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.logoinicio),
                        contentDescription = "logo pera",
                        modifier = Modifier.size(60.dp)
                    )
                    Text("Pera",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.displayMedium)
                }
                Text(
                    text = name,
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    )
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

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_c")
@Composable
fun TransferTabletPreview() {
    PeraAppTheme {
        TransferPageTablet(name = "Transferir"){
            TransferContentTablet()
        }
    }
}


