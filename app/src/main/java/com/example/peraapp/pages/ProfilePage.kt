package com.example.peraapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.peraapp.ui.theme.PeraAppTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.TopBar
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.components.isLandscape
import com.example.peraapp.components.isTablet


val profileItems = listOf(
    AppDestinations.TRANSFERIR,
    AppDestinations.INGRESAR,
    AppDestinations.COBRAR,
    AppDestinations.CERRARSESION
)

@Composable
fun ProfilePage(name: String,
                surname: String,
                mail: String,
                onNavigateToRoute: (String) -> Unit) {
    val configuration = LocalConfiguration.current
    val isTablet = isTablet(configuration)
    val isLandscape = isLandscape(configuration)

    if (isTablet) {
        if(isLandscape){
            ProfilePageTabletLandscape(name, surname, mail, onNavigateToRoute)
        }else{
            ProfilePageTabletPortrait(name, surname, mail, onNavigateToRoute)
        }
    } else {
        if(isLandscape){
            ProfilePagePhoneLandscape(name, surname, mail, onNavigateToRoute)
        }else{
            ProfilePagePhonePortrait(name, surname, mail, onNavigateToRoute)

        }
    }
}

@Composable
fun ProfilePagePhoneLandscape(
    name: String,
    surname: String,
    mail: String,
    onNavigateToRoute: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(R.string.cuenta, false)
        Row {
            ProfileHeader(
                name = name,
                surname = surname,
                mail = mail,
                imageSize = 150.dp,
                textStyleName = MaterialTheme.typography.titleLarge,
                textStyleMail = MaterialTheme.typography.titleMedium
            )

            Column {
                profileItems.forEach { item ->
                    ProfileButton(
                        iconResId = item.iconResId,
                        textResId = item.text,
                        onClick = { onNavigateToRoute(item.route) },
                        textStyle = MaterialTheme.typography.titleMedium,
                        iconSize = 28.dp
                    )
                }
            }
        }
    }
}


@Composable
fun ProfilePageTabletPortrait(
    name: String,
    surname: String,
    mail: String,
    onNavigateToRoute: (String) -> Unit
) {
    var showCobrarDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(R.string.cuenta),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 20.dp).align(Alignment.Start)
        )
        ProfileHeader(
            name = name,
            surname = surname,
            mail = mail,
            imageSize = 200.dp,
            textStyleName = MaterialTheme.typography.displayMedium,
            textStyleMail = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column (
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            profileItems.forEach { item ->
                ProfileButton(
                    iconResId = item.iconResId,
                    textResId = item.text,
                    onClick = {
                        when (item.route) {
                            AppDestinations.COBRAR.route -> showCobrarDialog = true
                            else -> onNavigateToRoute(item.route)
                        }
                    },
                    textStyle = MaterialTheme.typography.titleLarge,
                    iconSize = 46.dp
                )
            }
            Spacer(modifier = Modifier.padding(top = 40.dp))
            Image(
                painter = painterResource(id = R.drawable.fotocuentatablet),
                contentDescription = stringResource(R.string.fotocuentatablet),
                modifier = Modifier.size(400.dp).align(Alignment.CenterHorizontally).align(Alignment.End)
            )
        }
    }

    if (showCobrarDialog) {
        ChargeDialog(
            onDismissRequest = { showCobrarDialog = false },
            onConfirmation = { /* Lógica de confirmación */ showCobrarDialog = false },
            dialogTitle = stringResource(R.string.cobrar)
        )
    }

}


@Composable
fun ProfilePagePhonePortrait(
    name: String,
    surname: String,
    mail: String,
    onNavigateToRoute: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(R.string.cuenta)
        ProfileHeader(name, surname, mail)
        Column {
            profileItems.forEach { item ->
                ProfileButton(
                    iconResId = item.iconResId,
                    textResId = item.text,
                    onClick = { onNavigateToRoute(item.route) }
                )
            }
        }
    }
}


@Composable
fun ProfilePageTabletLandscape(name: String,
                      surname: String,
                      mail: String,
                      onNavigateToRoute: (String) -> Unit) {
    var showCobrarDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(R.string.cuenta),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 20.dp).align(Alignment.Start)
        )
        Row (
            modifier = Modifier.fillMaxSize()
        ){
            Column (
                modifier = Modifier.fillMaxHeight()
            ){
                ProfileHeader(
                    name = name,
                    surname = surname,
                    mail = mail,
                    imageSize = 200.dp,
                    textStyleName = MaterialTheme.typography.displayMedium,
                    textStyleMail = MaterialTheme.typography.displaySmall,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column (
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    profileItems.forEach { item ->
                        ProfileButton(
                            iconResId = item.iconResId,
                            textResId = item.text,
                            onClick = {
                                when (item.route) {
                                    AppDestinations.COBRAR.route -> showCobrarDialog = true
                                    else -> onNavigateToRoute(item.route)
                                }
                            },
                            textStyle = MaterialTheme.typography.titleLarge,
                            iconSize = 46.dp
                        )
                    }

                }
            }
            Image(
                painter = painterResource(id = R.drawable.fotocuentatablet),
                contentDescription = stringResource(R.string.fotocuentatablet),
                modifier = Modifier.size(400.dp).align(Alignment.Bottom)
            )
        }

    }
    //se podria modularizar esto de los dialogos
    if (showCobrarDialog) {
        ChargeDialog(
            onDismissRequest = { showCobrarDialog = false },
            onConfirmation = { /* Lógica de confirmación */ showCobrarDialog = false },
            dialogTitle = stringResource(R.string.cobrar)
        )
    }

}

@Composable
fun ProfileButton(
    iconResId: Int,
    textResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    iconSize: Dp = 28.dp
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier.padding(top = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 20.dp)
        ) {
            val icon: Painter = painterResource(id = iconResId)
            Image(
                painter = icon,
                contentDescription = stringResource(textResId),
                modifier = Modifier.size(iconSize)
            )
            Text(
                text = stringResource(textResId),
                style = textStyle,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}

@Composable
fun ProfileHeader(
    name: String,
    surname: String,
    mail: String,
    modifier: Modifier = Modifier,
    imageSize: Dp = 150.dp,
    textStyleName: TextStyle = MaterialTheme.typography.titleLarge,
    textStyleMail: TextStyle = MaterialTheme.typography.titleMedium
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.profiledefault),
            contentDescription = stringResource(R.string.fotodeperfil),
            modifier = Modifier.size(imageSize).clip(CircleShape)
        )
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = "$name $surname",
                style = textStyleName,
                modifier = Modifier.padding(5.dp)
            )
            Text(
                text = mail,
                style = textStyleMail,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}


@PreviewSizes
@Composable
fun previewprofile(){
    PeraAppTheme {
        ProfilePage(
            name = "Samanta",
            surname = "Jones",
            mail = "sjones@gmail.com",
        ) { }
    }
}