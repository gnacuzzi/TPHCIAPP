package com.example.peraapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.ui.theme.PeraAppTheme

@Composable
fun LoginPage(onNavigateToRoute: (String) -> Unit) {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet) {
        LoginPageTablet(onNavigateToRoute)
    } else {
        LoginPagePhone(onNavigateToRoute)
    }
}

@Composable
fun LoginPagePhone(onNavigateToRoute: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.fotoinicio),
                contentDescription = stringResource(R.string.fotoinicio),
                modifier = Modifier
                    .size(340.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.iniciodesesion),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* Manejar el cambio de valor */ },
                label = { Text(stringResource(R.string.mail)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                )
            )

            OutlinedTextField(
                value = "",
                onValueChange = { /* Manejar el cambio de valor */ },
                label = { Text(stringResource(R.string.contraseña)) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                )
            )

            Button(
                onClick = { onNavigateToRoute(AppDestinations.INICIO.route) },
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
                Text(stringResource(R.string.iniciarsesion), style = MaterialTheme.typography.titleMedium)
            }

            Button(
                onClick = { onNavigateToRoute(AppDestinations.REGISTRARME.route) },
                modifier = Modifier
                    .width(180.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 15.dp)
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
                Text(stringResource(R.string.registrarme), style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@Composable
fun LoginPageTablet(onNavigateToRoute: (String) -> Unit) {
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
                contentDescription = stringResource(R.string.fotoinicio),
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
                        text = stringResource(R.string.iniciodesesion),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Manejar el cambio de valor */ },
                        label = { Text(stringResource(R.string.mail)) },
                        modifier = Modifier.padding(top = 20.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email
                        )
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Manejar el cambio de valor */ },
                        label = { Text(stringResource(R.string.contraseña)) },
                        modifier = Modifier.padding(top = 20.dp, bottom = 30.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Password
                        )
                    )
                    Button(
                        onClick = { onNavigateToRoute(AppDestinations.INICIO.route)},
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
                        Text(stringResource(R.string.iniciarsesion), style = MaterialTheme.typography.titleMedium)
                    }

                    Button(
                        onClick = { onNavigateToRoute(AppDestinations.REGISTRARME.route) },
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
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Text(stringResource(R.string.registrarme), style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}


@PreviewSizes
@Composable
fun LoginPagePreview(){
    PeraAppTheme {
        LoginPage{

        }
    }
}