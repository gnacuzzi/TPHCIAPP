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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.peraapp.R
import com.example.peraapp.components.MovimientosSection
import com.example.peraapp.components.SaldoSection
import com.example.peraapp.components.TarjetasSection
import com.example.peraapp.components.TopBar

@Composable
fun HomePage(onNavigateToRoute: (String) -> Unit) {
    val configuration = LocalConfiguration.current
    if (configuration.screenWidthDp < 600){
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
                    .verticalScroll(rememberScrollState())//supuestamente lo hace scrolleable, veremos
            ) {
                TarjetasSection(onNavigateToRoute)
                MovimientosSection(onNavigateToRoute)
            }
        }
    }else{
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
            Column(modifier = Modifier.weight(0.4f)) {
                TarjetasSection(onNavigateToRoute)
            }
        }
    }
}

