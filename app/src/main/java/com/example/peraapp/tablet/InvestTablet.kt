package com.example.peraapp.tablet

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peraapp.ui.theme.PeraAppTheme

class InvestTablet {
}
@Composable
fun InvestPageTablet(name: String,
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

@Composable
fun InvestContentTablet() {
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
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp)
        ){
            Text(
                text = "Rescatar dinero",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Text(
                    text = "$4000", //plata a rescatar, mandar como parametro
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(top = 20.dp)
                )

                Button(
                    onClick = { /* Acción para rescatar */ },
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .width(270.dp)
                        .align(Alignment.CenterVertically),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Rescatar", style = MaterialTheme.typography.displaySmall)
                }
            }
            Text(
                text = "Ingresa el monto que deseas invertir:",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(top = 100.dp)
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text("Monto") },
                    modifier = Modifier
                        .padding(bottom = 10.dp, top = 20.dp, start = 30.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    textStyle = MaterialTheme.typography.titleLarge
                )

                Button(
                    onClick = { /* Acción para invertir */ },
                    modifier = Modifier
                        .padding(top = 40.dp, end = 50.dp)
                        .width(270.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Invertir", style = MaterialTheme.typography.displaySmall)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_c")
@Composable
fun InvestTabletPreview() {
    PeraAppTheme {
        InvestPageTablet(name = "Invertir o rescatar"){
            InvestContentTablet()
        }
    }
}