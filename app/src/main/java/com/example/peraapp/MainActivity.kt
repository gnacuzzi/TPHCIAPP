package com.example.peraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peraapp.ui.theme.PeraAppTheme
import androidx.compose.ui.graphics.painter.Painter


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeraAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MainScreen(
    name: String,
    bodyContent: @Composable () -> Unit
) {
    Scaffold(
        topBar = { TopBar(name) },
        bottomBar = {
            BottomBar(onCenterButtonClick = {
            })
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
}

@Composable
fun HomePage() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SaldoSection(
            name = "Samanta",
            saldo = 0
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())//supuestamente lo hace scrolleable, veremos
        ) {
            TarjetasSection()
            MovimientosSection()
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    PeraAppTheme {
        MainScreen("Inicio"){
            HomePage()
        }
    }
}

data class BottomBarItem(
    val iconResId: Int,
    val text: String,
    val onClick: () -> Unit
)

val bottomBarItems = listOf(
    BottomBarItem(
        iconResId = R.drawable.home,
        text = "Inicio",
        onClick = { /* Acción para "Inicio" */ }
    ),
    BottomBarItem(
        iconResId = R.drawable.movimientos,
        text = "Movimientos",
        onClick = { /* Acción para "Movimientos" */ }
    ),
    BottomBarItem(
        iconResId = R.drawable.tarjetas,
        text = "Tarjetas",
        onClick = { /* Acción para "Tarjetas" */ }
    ),
    BottomBarItem(
        iconResId = R.drawable.cuenta,
        text = "Cuenta",
        onClick = { /* Acción para "Cuenta" */ }
    )
)

@Composable
fun BottomBar(
    onCenterButtonClick: () -> Unit
) {
    Box {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.tertiary,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                bottomBarItems.forEach { item ->
                    Button(
                        onClick = item.onClick,
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            val icon: Painter = painterResource(id = item.iconResId)
                            Image(
                                painter = icon,
                                contentDescription = item.text,
                                modifier = Modifier.size(32.dp)
                            )
                            Text(
                                text = item.text,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = onCenterButtonClick,
            containerColor = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-35).dp)
                .size(75.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(R.drawable.qrpera),
                contentDescription = "QR Code",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(titleTopBar: String = "") {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.secondary,
        ),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoinicio),
                    contentDescription = "logo pera",
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = titleTopBar,
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    )
}

data class HomeBarItem(
    val iconResId: Int,
    val text: String,
    val onClick: () -> Unit
)

val homeItems = listOf(
    HomeBarItem(
        iconResId = R.drawable.transferir,
        text = "Transferir",
        onClick = { /* Acción para "Transferir" */ }
    ),
    HomeBarItem(
        iconResId = R.drawable.ingresar,
        text = "Ingresar",
        onClick = { /* Acción para "Ingresar" */ }
    ),
    HomeBarItem(
        iconResId = R.drawable.cobrar,
        text = "Cobrar",
        onClick = { /* Acción para "Cobrar" */ }
    )
)

@Composable
fun SaldoSection(name:String, saldo: Number) {
    Surface (
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(35.dp),
        modifier = Modifier.padding(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "¡Hola, $name!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Tu saldo actual es",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = "$$saldo",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                homeItems.forEach { item ->
                    Button(
                        onClick = item.onClick,
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.secondary
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            val icon: Painter = painterResource(id = item.iconResId)
                            Image(
                                painter = icon,
                                contentDescription = item.text,
                                modifier = Modifier.size(28.dp)
                            )
                            Text(
                                text = item.text,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetasSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Tarjetas",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()){
            CardHome(//esto deberia ser un foreach
                bank = "Santander",
                number = "1234 5678 9101 1121",
                name = "Samanta Jones",
                date = "12/28",
            )
            {}
            IconButton(
                onClick = {  }
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Agregar tarjeta",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(70.dp)
                )
            }
        }
    }
}

@Composable
fun MovimientosSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row (modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "Movimientos",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.secondary,
                    containerColor = MaterialTheme.colorScheme.background
                ),
            ) {
                Text(
                    text = "Ver más",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        MovimientoItem(
            name = "Steam",
            date = "ayer 16:20",
            amount = "-$120.69",
            color = Color.Red
        )

    }
}

