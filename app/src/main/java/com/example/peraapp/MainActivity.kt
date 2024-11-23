package com.example.peraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.peraapp.ui.theme.PeraAppTheme
import com.example.peraapp.pages.MainScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeraAppTheme {
                MainScreen()
            }
        }
    }
}
