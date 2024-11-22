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
import androidx.compose.runtime.collectAsState
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
import com.example.peraapp.HomeViewModel
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.ModularizedLayout
import com.example.peraapp.components.TopBar
import com.example.peraapp.data.model.Card
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.navigation.AppDestinationsHelper
import com.example.peraapp.ui.theme.PeraAppTheme

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
fun Card(
    bank: String,
    number: String,
    name: String,
    date: String,
    roundedCorner: Int = 16,
    paddingSurface: Int = 10,
    width: Int = 320,
    height: Int = 200,
    normalPadding: Int = 10,
    horizontalNumberPadding: Int = 20,
    onCardClick: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(roundedCorner.dp),
        modifier = Modifier
            .padding(paddingSurface.dp)
            .clickable(onClick = onCardClick) // Ejecuta la acción de clic
    ) {
        Column(
            modifier = Modifier
                .size(width = width.dp, height = height.dp)
                .padding(normalPadding.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Banco
            Text(
                text = bank,
                modifier = Modifier.align(Alignment.End),
                style = MaterialTheme.typography.titleMedium
            )

            // Número de tarjeta
            Text(
                text = number,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = horizontalNumberPadding.dp),
                style = MaterialTheme.typography.titleLarge
            )

            // Información de la tarjeta (Nombre y Fecha)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = normalPadding.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
fun CardsScreen(onNavigateToRoute: (String) -> Unit,
                viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val cards = uiState.cards ?: emptyList()

    ModularizedLayout(
        contentPhonePortrait = { CardsScreenPhonePortrait(onNavigateToRoute, cards) },
        contentPhoneLandscape = { CardsScreenPhoneLandscape(onNavigateToRoute, cards) },
        contentTabletPortrait = { CardsScreenTablet(onNavigateToRoute, cards, viewModel) },
        contentTabletLandscape = { CardsScreenTablet(onNavigateToRoute, cards, viewModel)}
    )
}


@Composable
fun CardsScreenPhonePortrait(onNavigateToRoute: (String) -> Unit,
                             cards: List<Card>) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(R.string.tarjetas)
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(cards.size) { index ->
                val card = cards[index]
                Card(
                    bank = card.type.name,
                    number = card.number,
                    name = card.fullName,
                    date = card.expirationDate
                ) {
                    onNavigateToRoute(AppDestinationsHelper.eliminarTarjetaRoute(card.id!!))
                }
            }
            item {
                AddCardButton(onNavigateToRoute)
            }
        }
    }
}

@Composable
fun CardsScreenPhoneLandscape(onNavigateToRoute: (String) -> Unit,
                              cards: List<Card>) {
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
                items(cards.size) { index ->
                    val card = cards[index]
                    Card(
                        bank = card.type.name,
                        number = card.number,
                        name = card.fullName,
                        date = card.expirationDate
                    ) {
                        onNavigateToRoute(AppDestinationsHelper.eliminarTarjetaRoute(card.id!!))
                    }
                }
            }
            AddCardButton(onNavigateToRoute)
        }
    }
}


@Composable
fun CardsScreenTablet(onNavigateToRoute: (String) -> Unit,
                      cards: List<Card>,
                      viewModel: HomeViewModel) {
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
                items(cards.size) { index ->
                    val card = cards[index]
                    CardTablet(
                        bank = card.type.name,
                        number = card.number,
                        name = card.fullName,
                        date = card.expirationDate
                    ) {
                        onNavigateToRoute(AppDestinationsHelper.eliminarTarjetaRoute(card.id!!))
                    }
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
            viewModel = viewModel
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
