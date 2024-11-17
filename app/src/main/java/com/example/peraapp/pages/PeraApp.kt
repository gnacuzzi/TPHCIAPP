package com.example.peraapp.pages

import androidx.annotation.StringRes
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
import androidx.compose.ui.res.stringResource
import com.example.peraapp.HomePage
import com.example.peraapp.PreviewLocales
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.BottomBar
import com.example.peraapp.components.SideBar
import com.example.peraapp.components.TopBar
import com.example.peraapp.ui.theme.PeraAppTheme

@Composable
fun MainScreen(
    @StringRes name: Int,
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
@PreviewLocales
@Composable
fun PeraAppPreview(){
    PeraAppTheme {
        MainScreen(
            name = R.string.inicio
        ){
            HomePage()
        }
    }
}