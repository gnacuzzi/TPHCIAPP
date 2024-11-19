package com.example.peraapp.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.TopBar
import com.example.peraapp.components.isLandscape
import com.example.peraapp.components.isTablet
import com.example.peraapp.ui.theme.PeraAppTheme
import com.example.peraapp.components.BackButton
import com.example.peraapp.utils.ModularizedFunctions.ModularizedCardLayout

//hay que recibir la tarjeta por parametro
@Composable
fun DeleteCardScreen() {
    ModularizedCardLayout (
        contentPhonePortrait = { DeleteCardScreenPhonePortrait() },
        contentPhoneLandscape = { DeleteCardScreenPhoneLandscape() },
        contentTabletPortrait = { DeleteCardScreenTabletPortrait() },
        contentTabletLandscape = { DeleteCardScreenTabletLandscape() }
    )
}
@Composable
fun DeleteCardScreenPhonePortrait() {
    val card = card(
        name = "Samanta Jones",
        bank = "Santander",
        number = "1234 5678 9101 1121",
        date = "12/28",
        code = 111
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(R.string.tarjeta)
        BackButton {  } //todavia no esta terminada esta parte
        Card(
            bank = card.bank,
            number = card.number,
            name = card.name,
            date = card.date
        ) {}
        DeleteCardDialogHandler(
            card = card,
            dialogTitle = stringResource(R.string.deseaelimiar),
            onDeleteConfirmed = {
                //para eliminar la tarjeta
                println("Tarjeta eliminada")
            }
        )
    }
}
@Composable
fun DeleteCardScreenPhoneLandscape() {
    val card = card(
        name = "Samanta Jones",
        bank = "Santander",
        number = "1234 5678 9101 1121",
        date = "12/28",
        code = 111
    )
    Column {
        TopBar(R.string.tarjeta, false)
        BackButton {  } //todavia no esta terminada esta parte
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    bank = card.bank,
                    number = card.number,
                    name = card.name,
                    date = card.date
                ) {}
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                DeleteCardDialogHandler(
                    card = card,
                    dialogTitle = stringResource(R.string.deseaelimiar),
                    onDeleteConfirmed = {
                        // Lógica para eliminar la tarjeta
                        println("Tarjeta eliminada")
                    }
                )
            }
        }
    }

}
@Composable
fun DeleteCardScreenTabletLandscape() {
    val card = card(
        name = "Samanta Jones",
        bank = "Santander",
        number = "1234 5678 9101 1121",
        date = "12/28",
        code = 111
    )
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize(),
        ) {
            Text(
                text = stringResource(R.string.tarjeta),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .align(Alignment.Start)
            )
            BackButton {  } //todavia no esta terminada esta parte
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                CardTablet(
                    bank = card.bank,
                    number = card.number,
                    name = card.name,
                    date = card.date
                ) {}
            }
        }

        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DeleteCardDialogHandler(
                card = card,
                dialogTitle = stringResource(R.string.deseaelimiar),
                onDeleteConfirmed = {
                    // Lógica para eliminar la tarjeta
                    println("Tarjeta eliminada")
                }
            )
        }
    }
}

@Composable
fun DeleteCardScreenTabletPortrait() {
    val card = card(
        name = "Samanta Jones",
        bank = "Santander",
        number = "1234 5678 9101 1121",
        date = "12/28",
        code = 111
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize(),
        ) {
            Text(
                text = stringResource(R.string.tarjeta),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .align(Alignment.Start)
            )
            BackButton {  } //todavia no esta terminada esta parte
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                CardTablet(
                    bank = card.bank,
                    number = card.number,
                    name = card.name,
                    date = card.date
                ) {}
            }
        }

        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DeleteCardDialogHandler(
                card = card,
                dialogTitle = stringResource(R.string.deseaelimiar),
                onDeleteConfirmed = {
                    // Lógica para eliminar la tarjeta
                    println("Tarjeta eliminada")
                }
            )
        }
    }
}

//me parece que la logica para eliminar se puede dejar fija asi no repetimos tanto codigo
@Composable
fun DeleteCardDialogHandler(
    card: card,
    dialogTitle: String,
    onDeleteConfirmed: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Estado del diálogo
    var showDialog by remember { mutableStateOf(false) }

    // Mostrar el botón que abre el diálogo
    Button(
        onClick = { showDialog = true },
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.tertiary,
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .width(320.dp)
    ) {
        Text(
            text = stringResource(R.string.eliminartarjeta),
            style = MaterialTheme.typography.titleLarge
        )
    }

    if (showDialog) {
        DeleteCardDialog(
            card = card,
            onDismissRequest = { showDialog = false },
            onConfirmation = {
                onDeleteConfirmed()
                showDialog = false
            },
            dialogTitle = dialogTitle
        )
    }
}


@PreviewSizes
@Composable
fun previewcard(){
    PeraAppTheme {
        DeleteCardScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteCardDialogPreview() {
    PeraAppTheme {
        DeleteCardDialog(
            card(//esto esta como ejemplo pero no se deberia construir ahi
                name = "Samanta Jones",
                bank = "Santander",
                number = "1234 1111 5678 2212",
                date = "12/28",
                code = 111
            ),
            onDismissRequest = { },
            onConfirmation = { },
            dialogTitle = "${stringResource(R.string.deseaelimiar)}?"
            //habria que pasar la tarjeta pero actualmente es una funcion no una clase
        )
    }
}

@Composable
fun DeleteCardDialog(
    card: card,
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
                //habria que mandarla como parametro
                CardHome(card.bank, card.number, card.name, card.date) {}

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
