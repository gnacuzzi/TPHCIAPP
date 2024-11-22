package com.example.peraapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.peraapp.HomeViewModel
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.ModularizedLayout
import com.example.peraapp.components.MovimientosSection
import com.example.peraapp.components.SaldoSection
import com.example.peraapp.components.TarjetasSection
import com.example.peraapp.components.TopBar
import com.example.peraapp.data.model.Card
import com.example.peraapp.data.model.Payment
import com.example.peraapp.ui.theme.PeraAppTheme



@Composable
fun HomeScreen(onNavigateToRoute: (String) -> Unit,
               viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    var balance = 0.0
    viewModel.getCurrentUser()
    if(uiState.walletDetail != null){
        balance = uiState.walletDetail!!.balance
    }

    var name = stringResource(R.string.desconocido)
    if (uiState.currentUser != null){
        name = uiState.currentUser!!.firstName
    }

    val cards = uiState.cards ?: emptyList()

    val payments = uiState.payments ?: emptyList()

    ModularizedLayout(
        contentPhonePortrait = { PhonePortraitHome(onNavigateToRoute, balance, name, cards, payments) },
        contentPhoneLandscape = { PhoneLandscapeHome(onNavigateToRoute, balance, name, payments) },
        contentTabletPortrait = { TabletPortraitHome(onNavigateToRoute, balance, name, cards, payments, viewModel) },
        contentTabletLandscape = { TabletLandscapeHome(onNavigateToRoute, balance, name, cards, payments, viewModel) }
    )
}

@Composable
fun PhonePortraitHome(onNavigateToRoute: (String) -> Unit,
                      saldo: Double,
                      name: String,
                      cards: List<Card>,
                      payments: List<Payment>) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(R.string.inicio)
        SaldoSection(
            name = name,
            saldo = saldo,
            onNavigateToRoute = onNavigateToRoute
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TarjetasSection(onNavigateToRoute, cards, null)
            MovimientosSection(onNavigateToRoute, payments, name)
        }
    }
}

@Composable
fun PhoneLandscapeHome(onNavigateToRoute: (String) -> Unit,
                       saldo: Double,
                       name: String,
                       payments: List<Payment>) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            SaldoSection(
                name = name,
                saldo = saldo,
                onNavigateToRoute = onNavigateToRoute
            )
        }
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            MovimientosSection(onNavigateToRoute, payments, name)
        }
    }
}
@Composable
fun TabletPortraitHome(onNavigateToRoute: (String) -> Unit,
                       saldo: Double,
                       name: String,
                       cards: List<Card>,
                       payments: List<Payment>,
                       viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column (
            modifier = Modifier.weight(0.6f)
        ){
            SaldoSection(
                name = name,
                saldo = saldo,
                onNavigateToRoute = onNavigateToRoute
            )
            TarjetasSection(onNavigateToRoute, cards, viewModel)
        }
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            MovimientosSection(onNavigateToRoute, payments, name)
        }
    }
}

@Composable
fun TabletLandscapeHome(onNavigateToRoute: (String) -> Unit,
                        saldo: Double,
                        name: String,
                        cards: List<Card>,
                        payments: List<Payment>,
                        viewModel: HomeViewModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight()
        ) {
            SaldoSection(
                name = name,
                saldo = saldo,
                onNavigateToRoute = onNavigateToRoute
            )
            MovimientosSection(onNavigateToRoute, payments, name)
        }
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            TarjetasSection(onNavigateToRoute, cards, viewModel)
        }
    }
}


@PreviewSizes
@Composable
fun HomePreview(){
    PeraAppTheme {
        MainScreen()
    }
}
