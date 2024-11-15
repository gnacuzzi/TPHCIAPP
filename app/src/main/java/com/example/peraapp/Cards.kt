package com.example.peraapp

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peraapp.ui.theme.PeraAppTheme

class Cards {
}


@Composable
fun CardHome(bank: String, number: String, name: String, date: String, onCardClick: () -> Unit){
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(10.dp).clickable(onClick = onCardClick) // no puse la cruz, veamos si podemos agregar otra pantalla
    ){
        Column (
            modifier = Modifier.size(width = 250.dp, height = 160.dp),
            verticalArrangement = Arrangement.SpaceAround){
            Text(
                text = bank,
                modifier = Modifier.align(Alignment.End).padding(horizontal = 10.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = number,
                modifier = Modifier.align(Alignment.Start).padding(horizontal = 10.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Row (modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun Card(bank: String, number: String, name: String, date: String, onCardClick: () -> Unit){
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(10.dp)
            .padding(bottom = 10.dp)
            .clickable(onClick = onCardClick) // no puse la cruz, veamos si podemos agregar otra pantalla
    ){
        Column (
            modifier = Modifier.size(width = 320.dp, height = 200.dp),
            verticalArrangement = Arrangement.SpaceAround){
            Text(
                text = bank,
                modifier = Modifier.align(Alignment.End).padding(horizontal = 10.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = number,
                modifier = Modifier.align(Alignment.Start).padding(horizontal = 20.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Row (modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = name,
                    modifier = Modifier.padding(horizontal = 10.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = date,
                    modifier = Modifier.padding(horizontal = 10.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}


@Composable
fun CardsPage(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),//supuestamente lo hace scrolleable, veremos
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(//esto deberia ser un foreach
            bank = "Santander",
            number = "1234 5678 9101 1121",
            name = "Samanta Jones",
            date = "12/28",
        )
        {}
        Card(//esto deberia ser un foreach
            bank = "Galicia",
            number = "1234 1111 9101 1121",
            name = "Samanta Jones",
            date = "12/26"
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
                text = "Agregar nueva tarjeta",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardsPagePreview() {
    PeraAppTheme {
        MainScreen("Tarjetas"){
            CardsPage(

            )
        }
    }
}

