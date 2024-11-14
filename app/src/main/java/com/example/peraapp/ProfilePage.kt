package com.example.peraapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.peraapp.ui.theme.PeraAppTheme

class ProfilePage {
}

@Composable
fun profilePage(){

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePagePreview() {
    PeraAppTheme {
        MainScreen("Cuenta"){
            profilePage()
        }
    }
}