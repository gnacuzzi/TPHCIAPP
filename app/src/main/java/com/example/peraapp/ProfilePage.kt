package com.example.peraapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.peraapp.ui.theme.PeraAppTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

class ProfilePage {
}

data class ProfileItem(
    val icon: ImageVector,
    val text: String,
    val onClick: () -> Unit
)

val profileItems = listOf(
    ProfileItem(
        icon = Icons.Outlined.MailOutline,
        text = "Tarjetas",
        onClick = {  }
    ),
    ProfileItem(
        icon = Icons.Outlined.Close,
        text = "Cerrar sesión",
        onClick = {  }
    )
)

@Composable
fun profilePage(name: String,
                surname: String,
                mail: String){
    Column (modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(id = R.drawable.profiledefault),
            contentDescription = "Imagen de perfil",
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
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.text,
                            modifier = Modifier.size(40.dp)
                        )
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 15.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePagePreview() {
    PeraAppTheme {
        MainScreen("Cuenta"){
            profilePage(
                name = "Samanta",
                surname = "Jones",
                mail = "sjones@gmail.com"
            )
        }
    }
}