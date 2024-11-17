package com.example.peraapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import com.example.peraapp.ui.theme.PeraAppTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import com.example.peraapp.components.TopBar

class AddCard {
}

@Composable
fun AddCardPage(bodycontent: @Composable () -> Unit){
    Scaffold(
        topBar = { TopBar("Agregar tarjeta") }
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
fun AddCardContent() {
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
            label = { Text("Número de tarjeta") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )


        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = { Text("Nombre del titular") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )
        )


        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = { Text("Fecha de vencimiento") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )

        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = { Text("Código de seguridad") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )

        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = { Text("Banco") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )
        )

        Button(
            onClick = { /* Acción para agregar tarjeta */ },
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
            Text("Agregar tarjeta", style = MaterialTheme.typography.titleMedium)
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddCardPagePreview() {
    PeraAppTheme{
        AddCardPage{
            AddCardContent()
        }
    }
}

