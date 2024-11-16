package com.example.peraapp.tablet

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peraapp.R
import com.example.peraapp.profileItems
import com.example.peraapp.ui.theme.PeraAppTheme

class ProfileTablet {
}

data class ProfileItemTablet(
    val iconResId: Int,
    val text: String,
    val onClick: () -> Unit
)

val profileItemsTablet = listOf(
    ProfileItemTablet(
        iconResId = R.drawable.transferir,
        text = "Transferir",
        onClick = {  }
    ),
    ProfileItemTablet(
        iconResId = R.drawable.ingresar,
        text = "Ingresar",
        onClick = {  }
    ),
    ProfileItemTablet(
        iconResId = R.drawable.cobrar,
        text = "Cobrar",
        onClick = {  }
    ),
    ProfileItemTablet(
        iconResId = R.drawable.invest,
        text = "Invertir o rescate",
        onClick = {  }
    ),
    ProfileItemTablet(
        iconResId = R.drawable.cerrarsesion,
        text = "Cerrar sesión",
        onClick = {  }
    )
)

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
                text = "Cuenta",
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
                        contentDescription = "Imagen de perfil",
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
                    profileItemsTablet.forEach { item ->
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
                                    contentDescription = item.text,
                                    modifier = Modifier.size(46.dp)
                                )
                                Text(
                                    text = item.text,
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
            contentDescription = "foto cuenta",
            modifier = Modifier.size(400.dp).align(Alignment.Bottom)
        )
    }
}



@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_c")
@Composable
fun ProfileTabletPreview() {
    PeraAppTheme {
        MainScreenTablet(){
            ProfilePageTablet(
                name = "Samanta",
                surname = "Jones",
                mail = "sjones@gmail.com"
            )
        }
    }
}