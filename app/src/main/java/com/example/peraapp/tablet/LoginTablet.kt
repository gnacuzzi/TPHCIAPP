package com.example.peraapp.tablet

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peraapp.R
import com.example.peraapp.ui.theme.PeraAppTheme

class LoginTablet {
}
@Composable
fun LoginPageTablet() {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Image(//no quedo bien el color, cambiarla
                painter = painterResource(id = R.drawable.fotoiniciotablet),
                contentDescription = "foto cuenta",
                modifier = Modifier.size(600.dp),
            )
            Surface (
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.size(width = 500.dp, height = 500.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Inicio de Sesión",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Manejar el cambio de valor */ },
                        label = { Text("Mail") },
                        modifier = Modifier.padding(top = 20.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email
                        )
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Manejar el cambio de valor */ },
                        label = { Text("Contraseña") },
                        modifier = Modifier.padding(top = 20.dp, bottom = 30.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password
                        )
                    )
                    Button(
                        onClick = { /* Acción para iniciar sesión */ },
                        modifier = Modifier
                            .width(180.dp)
                            .align(Alignment.CenterHorizontally)
                            .height(50.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.background,
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text("Iniciar sesión", style = MaterialTheme.typography.titleMedium)
                    }

                    Button(
                        onClick = { /* Acción para registrarme */ },
                        modifier = Modifier
                            .width(180.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 15.dp, top = 15.dp)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(4.dp)
                            ),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.secondary,
                            containerColor = MaterialTheme.colorScheme.background
                        )
                    ) {
                        Text("Registrarme", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_c")
@Composable
fun LoginTabletPreview() {
    PeraAppTheme {
        LoginPageTablet()
    }
}