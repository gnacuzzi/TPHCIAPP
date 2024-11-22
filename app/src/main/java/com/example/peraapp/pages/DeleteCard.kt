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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.HomeViewModel
import com.example.peraapp.R
import com.example.peraapp.components.TopBar
import com.example.peraapp.components.ModularizedLayout
import com.example.peraapp.data.model.Card
import com.example.peraapp.navigation.AppDestinations

@Composable
fun DeleteCardScreen(viewModel: HomeViewModel, cardId: Int?, onNavigateToRoute: (String) -> Unit){
    val uiState by viewModel.uiState.collectAsState()
    val card = uiState.cards?.find { it.id == cardId }

    if (card == null) {
        onNavigateToRoute(AppDestinations.TARJETAS.route)
    } else {
        ModularizedLayout(
            contentPhonePortrait = { DeleteCardScreenPhonePortrait(card, viewModel, onNavigateToRoute) },
            contentPhoneLandscape = { DeleteCardScreenPhoneLandscape(card, viewModel, onNavigateToRoute) },
            contentTabletPortrait = { DeleteCardScreenTabletPortrait(card, viewModel, onNavigateToRoute) },
            contentTabletLandscape = { DeleteCardScreenTabletLandscape(card, viewModel, onNavigateToRoute) }
        )
    }
}

@Composable
fun DeleteCardScreenPhonePortrait(card: Card?, viewModel: HomeViewModel, onNavigateToRoute: (String) -> Unit) {
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
            if (card != null) {
                Card(
                    bank = card.type.name,
                    number = card.number,
                    name = card.fullName,
                    date = card.expirationDate
                ) {}
            }
            if (card != null) {
                DeleteCardDialogHandler(
                    card = card,
                    dialogTitle = stringResource(R.string.deseaelimiar),
                    onDeleteConfirmed = {
                        card.id?.let { viewModel.deleteCard(it) }
                    },
                    onNavigateToRoute = onNavigateToRoute
                )
            }
        }
    }
}

@Composable
fun DeleteCardScreenPhoneLandscape(card: Card?, viewModel: HomeViewModel, onNavigateToRoute: (String) -> Unit) {
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
                if (card != null) {
                    Card(
                        bank = card.type.name,
                        number = card.number,
                        name = card.fullName,
                        date = card.expirationDate
                    ) {}
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
                            card.id?.let { viewModel.deleteCard(it) }
                        },
                        onNavigateToRoute = onNavigateToRoute
                    )
                }
            }
        }
    }
}

@Composable
fun DeleteCardScreenTabletLandscape(card: Card?, viewModel: HomeViewModel, onNavigateToRoute: (String) -> Unit) {
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
                if (card != null) {
                    CardTablet(
                        bank = card.type.name,
                        number = card.number,
                        name = card.fullName,
                        date = card.expirationDate
                    ) {}
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
                        card.id?.let { viewModel.deleteCard(it) }
                    },
                    onNavigateToRoute = onNavigateToRoute
                )
            }
        }
    }
}

@Composable
fun DeleteCardScreenTabletPortrait(card: Card?, viewModel: HomeViewModel, onNavigateToRoute: (String) -> Unit) {
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
                if (card != null) {
                    CardTablet(
                        bank = card.type.name,
                        number = card.number,
                        name = card.fullName,
                        date = card.expirationDate
                    ) {}
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
                        card.id?.let { viewModel.deleteCard(it) }
                    },
                    onNavigateToRoute = onNavigateToRoute
                )
            }
        }
    }
}

@Composable
fun DeleteCardDialogHandler(
    card: Card,
    dialogTitle: String = stringResource(R.string.deseaelimiar),
    onDeleteConfirmed: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToRoute: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

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
            onDismissRequest =
            {
                showDialog = false
            },
            onConfirmation = {
                onDeleteConfirmed()
                showDialog = false
            },
            dialogTitle = dialogTitle
        )
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
