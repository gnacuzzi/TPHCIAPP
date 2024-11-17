package com.example.peraapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.example.peraapp.HomePage
import com.example.peraapp.PreviewSizes
import com.example.peraapp.components.BottomBar
import com.example.peraapp.components.SideBar
import com.example.peraapp.components.TopBar
import com.example.peraapp.ui.theme.PeraAppTheme

@Composable
fun MainScreen(
    name: String,
    bodyContent: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    if (configuration.screenWidthDp < 600){
        Scaffold(
            topBar = { TopBar(name) },
            bottomBar = {
                BottomBar()
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                bodyContent()
            }
        }
    } else{
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                SideBar()
                bodyContent()
            }
        }
    }
}

@PreviewSizes
@Composable
fun PeraAppPreview(){
    PeraAppTheme {
        MainScreen("Inicio"){
            HomePage()
        }
    }
}