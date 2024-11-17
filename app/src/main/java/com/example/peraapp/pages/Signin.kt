package com.example.peraapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.ui.theme.PeraAppTheme

@Composable
fun SigninPage() {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet) {
        SigninPageTablet()
    } else {
        SigninPagePhone()
    }
}

@Composable
fun SigninPagePhone() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.fotoinicio),
                contentDescription = "Login Image",
                modifier = Modifier
                    .size(300.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    IconButton(
                        onClick = { /* Aquí iría la acción para volver atrás */ },
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Text(
                        text = "Comenza en Pera",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Manejar el cambio de valor */ },
                        label = { Text("Nombre") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Manejar el cambio de valor */ },
                        label = { Text("Apellido") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Manejar el cambio de valor */ },
                        label = { Text("DNI") },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Manejar el cambio de valor */ },
                        label = { Text("Fecha de nacimiento",
                                style=MaterialTheme.typography.bodyMedium)},
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("DD/MM/AAAA") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            }

            item {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text("Mail") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    )
                )
            }

            item {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    )
                )
            }

            item {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text("Confirmar Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    )
                )
            }

            item {
                Button(
                    onClick = { /* Acción para registrarme */ },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(vertical = 16.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.background,
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Registrarme", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun SigninPageTablet() {
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
                modifier = Modifier.size(width = 600.dp, height = 480.dp)
            ){
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                    ) {
                        IconButton(
                            onClick = { /* Aquí iría la acción para volver atrás */ },
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver atrás",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Text(
                            text = "Comenza en Pera",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.width(400.dp)
                    ) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* Manejar el cambio de valor */ },
                            label = { Text("Nombre") },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* Manejar el cambio de valor */ },
                            label = { Text("Apellido") },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.width(400.dp)
                    ) {
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* Manejar el cambio de valor */ },
                            label = { Text("DNI") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            )
                        )
                        OutlinedTextField(
                            value = "",
                            onValueChange = { /* Manejar el cambio de valor */ },
                            label = { Text("Fecha de nacimiento",
                                style=MaterialTheme.typography.bodyMedium)},
                            modifier = Modifier.weight(1f),
                            placeholder = { Text("DD/MM/AAAA") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            )
                        )
                    }

                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Manejar el cambio de valor */ },
                        label = { Text("Mail") },
                        modifier = Modifier.width(400.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email
                        )
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Manejar el cambio de valor */ },
                        label = { Text("Contraseña") },
                        modifier = Modifier.width(400.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password
                        )
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Manejar el cambio de valor */ },
                        label = { Text("Confirmar Contraseña") },
                        modifier = Modifier.width(400.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password
                        )
                    )

                    Button(
                        onClick = { /* Acción para registrarme */ },
                        modifier = Modifier
                            .width(180.dp)
                            .padding(vertical = 16.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.background,
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text("Registrarme", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}

@PreviewSizes
@Composable
fun SigninPagePreview(){
    PeraAppTheme {
        SigninPage()
    }
}