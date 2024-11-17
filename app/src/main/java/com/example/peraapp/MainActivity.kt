package com.example.peraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.peraapp.ui.theme.PeraAppTheme
import androidx.compose.ui.platform.LocalConfiguration
import com.example.peraapp.components.MovimientosSection
import com.example.peraapp.components.SaldoSection
import com.example.peraapp.components.TarjetasSection
import com.example.peraapp.pages.MainScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeraAppTheme {
                MainScreen(
                    name = R.string.inicio
                ) {
                    HomePage()
                }
            }
        }
    }
}

@Composable
fun HomePage() {
    val configuration = LocalConfiguration.current
    if (configuration.screenWidthDp < 600){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SaldoSection(
                name = "Samanta",
                saldo = 0
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())//supuestamente lo hace scrolleable, veremos
            ) {
                TarjetasSection()
                MovimientosSection()
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
                    saldo = 0
                )
                MovimientosSection()
            }
            Column(modifier = Modifier.weight(0.4f)) {
                TarjetasSection()
            }
        }
    }
}

