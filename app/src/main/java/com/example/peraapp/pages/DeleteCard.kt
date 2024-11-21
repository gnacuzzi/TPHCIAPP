package com.example.peraapp.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.peraapp.HomeViewModel
import com.example.peraapp.PeraApplication
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.TopBar
import com.example.peraapp.ui.theme.PeraAppTheme
import com.example.peraapp.components.ModularizedLayout
import kotlinx.coroutines.delay
import com.example.peraapp.data.model.Card
import com.example.peraapp.data.model.CardType

//hay que recibir la tarjeta por parametro
@Composable
fun DeleteCardScreen(    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(
    LocalContext.current.applicationContext as PeraApplication
))
){
    val uiState by viewModel.uiState.collectAsState()
    val card = uiState.currentCard

    ModularizedLayout(
        contentPhonePortrait = { DeleteCardScreenPhonePortrait(card) },
        contentPhoneLandscape = { DeleteCardScreenPhoneLandscape(card) },
        contentTabletPortrait = { DeleteCardScreenTabletPortrait(card) },
        contentTabletLandscape = { DeleteCardScreenTabletLandscape(card) }
    )
}

@Composable
fun DeleteCardScreenPhonePortrait(card: Card?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        TopBar(R.string.tarjeta)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            card?.number?.let {
                Card(
                    number = card.number,
                    fullName = card.fullName,
                    expirationDate = card.expirationDate,
                    id = card.id,
                    cvv = card.cvv,
                    type = CardType.DEBIT,
                    createdAt = card.createdAt
                )
            }
            if (card != null) {
                DeleteCardDialogHandler(
                    card = card,
                    dialogTitle = stringResource(R.string.deseaelimiar),
                    onDeleteConfirmed = {
                        println("Tarjeta eliminada")
                    }
                )
            }
        }
    }
}

@Composable
fun DeleteCardScreenPhoneLandscape(card: Card?) {
    Column {
        TopBar(R.string.tarjeta, false)
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
                card?.number?.let {
                    Card(
                        number = card.number,
                        fullName = card.fullName,
                        expirationDate = card.expirationDate,
                        id = card.id,
                        cvv = card.cvv,
                        type = CardType.DEBIT,
                        createdAt = card.createdAt
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (card != null) {
                    DeleteCardDialogHandler(
                        card = card,
                        dialogTitle = stringResource(R.string.deseaelimiar),
                        onDeleteConfirmed = {
                            println("Tarjeta eliminada")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DeleteCardScreenTabletLandscape(card: Card?) {
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
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                card?.number?.let {
                    Card(
                        number = card.number,
                        fullName = card.fullName,
                        expirationDate = card.expirationDate,
                        id = card.id,
                        cvv = card.cvv,
                        type = CardType.DEBIT,
                        createdAt = card.createdAt
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (card != null) {
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
fun DeleteCardScreenTabletPortrait(card: Card?) {
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
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                card?.number?.let {
                    Card(
                        number = card.number,
                        fullName = card.fullName,
                        expirationDate = card.expirationDate,
                        id = card.id,
                        cvv = card.cvv,
                        type = CardType.DEBIT,
                        createdAt = card.createdAt
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (card != null) {
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

//me parece que la logica para eliminar se puede dejar fija asi no repetimos tanto codigo
@Composable
fun DeleteCardDialogHandler(
    card: Card,
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
fun Previewcard(){
    PeraAppTheme {
        DeleteCardScreen()
    }
}

@Composable
fun DeleteCardDialogPreview(card: Card?) {
    PeraAppTheme {
        if (card != null) {
            DeleteCardDialog(
                card = card,
                onDismissRequest = { },
                onConfirmation = { },
                dialogTitle = "${stringResource(R.string.deseaelimiar)}?"
                //habria que pasar la tarjeta pero actualmente es una funcion no una clase
            )
        }
    }
}

@Composable
fun DeleteCardDialog(
    card: Card,
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
                CardHome(card.type.name, card.number, card.fullName, card.expirationDate) {}

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

@Preview(showBackground = true)
@Composable
fun DeleteCardDialogStatePreview() {
    PeraAppTheme {
        DeleteCardDialogState(
            onDismissRequest = { /* seria eliminarse nomas */ },
            dialogTitle = stringResource(R.string.estadoeliminartarjeta)
        )
    }
}

@Composable
fun DeleteCardDialogState(
    onDismissRequest: () -> Unit,
    dialogTitle: String,
    dismissAfterMillis: Long = 3000,
    state: Boolean = true
) {
    var addText = stringResource(R.string.correcto)
    if (!state){
        addText = stringResource(R.string.fallo)
    }
    LaunchedEffect(Unit) {
        delay(dismissAfterMillis)
        onDismissRequest()
    }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
        ) {
            Text(
                text = "$dialogTitle $addText",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}



