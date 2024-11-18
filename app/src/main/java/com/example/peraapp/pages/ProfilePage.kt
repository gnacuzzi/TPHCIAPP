package com.example.peraapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.peraapp.ui.theme.PeraAppTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.TopBar

data class ProfileItem(
    val iconResId: Int,
    val text: Int,
    val onClick: () -> Unit
)

val profileItems = listOf(
    ProfileItem(
        iconResId = R.drawable.transferir,
        text = R.string.transferir,
        onClick = {  }
    ),
    ProfileItem(
        iconResId = R.drawable.ingresar,
        text = R.string.ingresar,
        onClick = {  }
    ),
    ProfileItem(
        iconResId = R.drawable.cobrar,
        text = R.string.cobrar,
        onClick = {  }
    ),
    ProfileItem(
        iconResId = R.drawable.invest,
        text = R.string.invertir,
        onClick = {  }
    ),
    ProfileItem(
        iconResId = R.drawable.cerrarsesion,
        text = R.string.cerrarsesion,
        onClick = {  }
    )
)

@Composable
fun ProfilePage(name: String,
                surname: String,
                mail: String) {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet) {
        ProfilePageTablet(name, surname, mail)
    } else {
        ProfilePagePhone(name, surname, mail)
    }
}

@Composable
fun ProfilePagePhone(
                name: String,
                surname: String,
                mail: String){
    Column (modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
        TopBar(R.string.cuenta)
        Image(
            painter = painterResource(id = R.drawable.profiledefault),
            contentDescription = stringResource(R.string.fotodeperfil),
            modifier = Modifier.size(150.dp).clip(CircleShape)
        )
        Text(text = "$name $surname",
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Text(mail,
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Column {
            profileItems.forEach { item ->
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp)
                    ) {
                        val icon: Painter = painterResource(id = item.iconResId)
                        Image(
                            painter = icon,
                            contentDescription = stringResource(item.text),
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            text = stringResource(item.text),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 15.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfilePageTablet(name: String,
                      surname: String,
                      mail: String) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.cuenta),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(bottom = 20.dp).align(Alignment.Start)
            )
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.profiledefault),
                        contentDescription = stringResource(R.string.fotodeperfil),
                        modifier = Modifier.size(200.dp).clip(CircleShape)
                    )
                    Column (
                        modifier = Modifier.padding(start = 50.dp)
                    ){
                        Text(text = "$name $surname",
                            modifier = Modifier.padding(5.dp),
                            style = MaterialTheme.typography.displayMedium
                        )
                        Text(mail,
                            modifier = Modifier.padding(5.dp),
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                }
                Column {
                    profileItems.forEach { item ->
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.secondary,
                                containerColor = MaterialTheme.colorScheme.background
                            ),
                            modifier = Modifier.padding(top = 20.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(start = 20.dp)
                            ) {
                                val icon: Painter = painterResource(id = item.iconResId)
                                Image(
                                    painter = icon,
                                    contentDescription = stringResource(item.text),
                                    modifier = Modifier.size(46.dp)
                                )
                                Text(
                                    text = stringResource(item.text),
                                    style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier.padding(start = 15.dp)
                                )
                            }
                        }
                    }
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
