package com.example.peraapp.pages

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.PreviewSizes
import com.example.peraapp.ui.theme.PeraAppTheme

//habria que mandar tambien el textstyle pero que lo haga otro
@Composable
fun CardHome(bank: String, number: String, name: String, date: String, onCardClick: () -> Unit){
    Card(
        bank = bank,
        number = number,
        name = name,
        date = date,
        width = 250,
        height = 160,
        horizontalNumberPadding = 10
    ) { onCardClick() }
}

@Composable
fun CardHomeTablet(bank: String, number: String, name: String, date: String, onCardClick: () -> Unit){
    Card(
        bank = bank,
        number = number,
        name = name,
        date = date,
        width = 300,
        height = 180,
        horizontalNumberPadding = 10,
        normalPadding = 15
    ) { onCardClick() }
}

@Composable
fun CardTablet(bank: String, number: String, name: String, date: String, onCardClick: () -> Unit){
    Card(
        bank = bank,
        number = number,
        name = name,
        date = date,
        width = 420,
        height = 260,
        horizontalNumberPadding = 25,
        normalPadding = 15
    ) { onCardClick() }
}

@Composable
fun Card(bank: String,
         number: String,
         name: String,
         date: String,
         roundedCorner: Int = 16,
         paddingSurface: Int = 10,
         width: Int = 320,
         height: Int = 200,
         normalPadding: Int = 10,
         horizontalNumberPadding: Int = 20,
         onCardClick: () -> Unit){
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(roundedCorner.dp),
        modifier = Modifier
            .padding(paddingSurface.dp)
            .padding(bottom = paddingSurface.dp)
            .clickable(onClick = onCardClick) // no puse la cruz, veamos si podemos agregar otra pantalla
    ){
        Column (
            modifier = Modifier.size(width = width.dp, height = height.dp),
            verticalArrangement = Arrangement.SpaceAround){
            Text(
                text = bank,
                modifier = Modifier.align(Alignment.End).padding(horizontal = normalPadding.dp),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = number,
                modifier = Modifier.align(Alignment.Start).padding(horizontal = horizontalNumberPadding.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Row (modifier = Modifier.fillMaxWidth().padding(start = normalPadding.dp, end = normalPadding.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = name,
                    modifier = Modifier.padding(horizontal = normalPadding.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = date,
                    modifier = Modifier.padding(horizontal = normalPadding.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun CardsPage() {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet) {
        CardsPageTablet()
    } else {
        CardsPagePhone()
    }
}

@Composable
fun CardsPagePhone(){
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
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
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

@PreviewSizes
@Composable
fun CardsPagePreview() {
    PeraAppTheme {
        MainScreen("Tarjetas"){
            CardsPage()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddCardTabletDialogPreview() {
    PeraAppTheme {
        AddCardTabletDialog(
            onDismissRequest = { },
            onConfirmation = { },
        )
    }
}

//hay que ver como hacemos para que en la navegacion cambie esto
@Composable
fun AddCardTabletDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver atrás",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(100.dp)
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
                    Text("Agregar tarjeta", style = MaterialTheme.typography.titleMedium)
                }

            }
        }
    }
}




