package com.example.peraapp.tablet

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

class CardsTablet {
}

@Composable
fun CardsPageTablet() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tarjetas",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(bottom = 20.dp).align(Alignment.Start)
            )
            Column (
                modifier = Modifier.verticalScroll(rememberScrollState())
            ){
                //aca iria un foreach
                CardTablet(//esto deberia ser un foreach
                    bank = "Galicia",
                    number = "1234 1111 9101 1121",
                    name = "Samanta Jones",
                    date = "12/26"
                ){}
            }
        }
        Column(
            modifier = Modifier.weight(0.5f).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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
                    .width(400.dp)
                    .height(80.dp)
            ) {
                Text(
                    text = "Agregar nueva tarjeta",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_c")
@Composable
fun CardsTabletPreview() {
    PeraAppTheme {
        MainScreenTablet(){
            CardsPageTablet()
        }
    }
}


@Composable
fun CardHomeTablet(bank: String, number: String, name: String, date: String, onCardClick: () -> Unit){
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(10.dp).clickable(onClick = onCardClick) // no puse la cruz, veamos si podemos agregar otra pantalla
    ){
        Column (
            modifier = Modifier.size(width = 300.dp, height = 180.dp),
            verticalArrangement = Arrangement.SpaceAround){
            Text(
                text = bank,
                modifier = Modifier.align(Alignment.End).padding(horizontal = 15.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = number,
                modifier = Modifier.align(Alignment.Start).padding(horizontal = 15.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Row (modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Composable
fun CardTablet(bank: String, number: String, name: String, date: String, onCardClick: () -> Unit){
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(10.dp)
            .padding(bottom = 10.dp)
            .clickable(onClick = onCardClick)
    ){
        Column (
            modifier = Modifier.size(width = 420.dp, height = 260.dp),
            verticalArrangement = Arrangement.SpaceAround){
            Text(
                text = bank,
                modifier = Modifier.align(Alignment.End).padding(horizontal = 15.dp),
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = number,
                modifier = Modifier.align(Alignment.Start).padding(horizontal = 25.dp),
                style = MaterialTheme.typography.displaySmall
            )
            Row (modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = name,
                    modifier = Modifier.padding(horizontal = 15.dp),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = date,
                    modifier = Modifier.padding(horizontal = 15.dp),
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }
}

