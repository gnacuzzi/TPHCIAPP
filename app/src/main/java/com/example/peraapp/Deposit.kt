package com.example.peraapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peraapp.ui.theme.PeraAppTheme

class Deposit {
}

@Composable
fun DepositPage(bodycontent: @Composable () -> Unit){
    Scaffold(
        topBar = { TopBar("Ingresar") }
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
@Composable
fun DepositContent() {
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
        Text(
            text = "Tu CBU",
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
            text = "Tu Alias",
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
            Text("Compartir datos", style = MaterialTheme.typography.titleMedium)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DepositPagePreview() {
    PeraAppTheme {
        DepositPage {
            DepositContent()
        }
    }
}
