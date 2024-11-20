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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.ModularizedLayout
import com.example.peraapp.components.TopBar
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.ui.theme.PeraAppTheme


data class Card (
    val bank: String,
    val number: String,
    val name: String,
    val date: String,
    val code: Int
)

//habria que mandar tambien el textstyle pero que lo haga otro
//hay que hacer que el cardclick sea ir a la de eliminar tarjeta
//esa hay que hacerla dinamica pero vamos a tener que mandarle el id de la tarjeta
//para eso necesitamos la api para definir bien que ponemos como id
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
fun CardsScreen(onNavigateToRoute: (String) -> Unit) {
    ModularizedLayout(
        contentPhonePortrait = { CardsScreenPhonePortrait(onNavigateToRoute) },
        contentPhoneLandscape = { CardsScreenPhoneLandscape(onNavigateToRoute) },
        contentTabletPortrait = { CardsScreenTablet(onNavigateToRoute) },
        contentTabletLandscape = { CardsScreenTablet(onNavigateToRoute)}
    )
}


@Composable
fun CardsScreenPhonePortrait(onNavigateToRoute: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(R.string.tarjetas)
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Card(//esto deberia ser un foreach
                    bank = "Santander",
                    number = "1234 5678 9101 1121",
                    name = "Samanta Jones",
                    date = "12/28",
                )
                {onNavigateToRoute(AppDestinations.ELIMINARTARJETA.route)}
            }
            item {
                AddCardButton(onNavigateToRoute)
            }
        }
    }
}

@Composable
fun CardsScreenPhoneLandscape(onNavigateToRoute: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(R.string.tarjetas, false)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Card(//esto deberia ser un foreach
                        bank = "Santander",
                        number = "1234 5678 9101 1121",
                        name = "Samanta Jones",
                        date = "12/28",
                    )
                    {onNavigateToRoute(AppDestinations.ELIMINARTARJETA.route)}                }
            }
            AddCardButton(onNavigateToRoute)
        }
    }
}


@Composable
fun CardsScreenTablet(onNavigateToRoute: (String) -> Unit) {
    var showAddCardDialog by remember { mutableStateOf(false) }
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
                text = stringResource(R.string.tarjetas),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(bottom = 20.dp).align(Alignment.Start)
            )
            LazyColumn {
                // Esto podría ser un foreach
                item {
                    CardTablet(
                        bank = "Galicia",
                        number = "1234 1111 9101 1121",
                        name = "Samanta Jones",
                        date = "12/26"
                    ) {onNavigateToRoute(AppDestinations.ELIMINARTARJETA.route)}
                }
            }
        }
        Column(
            modifier = Modifier.weight(0.5f).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { showAddCardDialog = true }, // Abre el diálogo al hacer clic
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
                    text = stringResource(R.string.agregarnuevatarjeta),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }

    if (showAddCardDialog) {
        AddCardTabletDialog(
            onDismissRequest = { showAddCardDialog = false },
            onConfirmation = {
                showAddCardDialog = false
                // Agrega la lógica para confirmar
            }
        )
    }
}

@Composable
fun AddCardButton(onNavigateToRoute: (String) -> Unit) {
    Button(
        onClick = { onNavigateToRoute(AppDestinations.AGREGARTARJETA.route) },
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
            text = stringResource(R.string.agregarnuevatarjeta),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@PreviewSizes
@Composable
fun Cardscreenpreview(){
    PeraAppTheme {
        CardsScreen {

        }
    }
}


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
                        contentDescription = stringResource(R.string.volveratras),
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(100.dp)
                    )
                }

                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text(stringResource(R.string.numerotarjeta)) },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )


                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text(stringResource(R.string.nombretitular)) },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    )
                )


                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text(stringResource(R.string.fechadeven)) },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text(stringResource(R.string.codigo)) },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text(stringResource(R.string.banco)) },
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
                    Text(stringResource(R.string.agregarnuevatarjeta), style = MaterialTheme.typography.titleMedium)
                }

            }
        }
    }
}
