package com.example.peraapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.ModularizedLayout
import com.example.peraapp.components.MovimientosSection
import com.example.peraapp.components.SaldoSection
import com.example.peraapp.components.TarjetasSection
import com.example.peraapp.components.TopBar
import com.example.peraapp.ui.theme.PeraAppTheme



@Composable
fun HomeScreen(onNavigateToRoute: (String) -> Unit) {
    ModularizedLayout(
        contentPhonePortrait = { PhonePortraitHome(onNavigateToRoute) },
        contentPhoneLandscape = { PhoneLandscapeHome(onNavigateToRoute) },
        contentTabletPortrait = { TabletPortraitHome(onNavigateToRoute) },
        contentTabletLandscape = { TabletLandscapeHome(onNavigateToRoute) }
    )
}

@Composable
fun PhonePortraitHome(onNavigateToRoute: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(R.string.inicio)
        SaldoSection(
            name = "Samanta",
            saldo = 0,
            onNavigateToRoute = onNavigateToRoute
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TarjetasSection(onNavigateToRoute)
            MovimientosSection(onNavigateToRoute)
        }
    }
}

@Composable
fun PhoneLandscapeHome(onNavigateToRoute: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            SaldoSection(
                name = "Samanta",
                saldo = 0,
                onNavigateToRoute = onNavigateToRoute
            )
        }
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            MovimientosSection(onNavigateToRoute)
        }
    }
}
@Composable
fun TabletPortraitHome(onNavigateToRoute: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column (
            modifier = Modifier.weight(0.6f)
        ){
            SaldoSection(
                name = "Samanta",
                saldo = 0,
                onNavigateToRoute = onNavigateToRoute
            )
            TarjetasSection(onNavigateToRoute)
        }
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            MovimientosSection(onNavigateToRoute)
        }
    }
}

@Composable
fun TabletLandscapeHome(onNavigateToRoute: (String) -> Unit) {
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
                name = "Samanta",
                saldo = 0,
                onNavigateToRoute = onNavigateToRoute
            )
            MovimientosSection(onNavigateToRoute)
        }
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            TarjetasSection(onNavigateToRoute)
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
