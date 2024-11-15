package com.example.peraapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peraapp.ui.theme.PeraAppTheme

class DeleteCard {
}

@Composable
fun DeleteCardPage(){//deberia recibir como parametro la tarjeta pero no se si eso se puede hacer
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(//esto deberia ser un foreach
            bank = "Santander",
            number = "1234 5678 9101 1121",
            name = "Samanta Jones",
            date = "12/28",
        )
        {}
        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.tertiary,
                containerColor = MaterialTheme.colorScheme.background
            ),
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .width(320.dp)
        ) {
            Text(
                text = "Eliminar esta tarjeta",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DeleteCardPagePreview() {
    PeraAppTheme {
        MainScreen("Tarjeta"){
            DeleteCardPage(
            )
        }
    }
}
