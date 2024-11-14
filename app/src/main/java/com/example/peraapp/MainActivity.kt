package com.example.peraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.peraapp.ui.theme.PeraAppTheme

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
fun MainScreen() {
    Scaffold(
        topBar = { TopBar("Inicio") },
        bottomBar = { BottomBar() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            SaldoSection()
            TarjetasSection()
            MovimientosSection()
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    PeraAppTheme {
        MainScreen()
    }
}

data class BottomBarItem(
    val icon: ImageVector,
    val text: String,
    val onClick: () -> Unit
)

val bottomBarItems = listOf(
    BottomBarItem(
        icon = Icons.Outlined.Home,
        text = "Inicio",
        onClick = { /* Acción para "Inicio" */ }
    ),
    BottomBarItem(
        icon = Icons.Outlined.DateRange,
        text = "Movimientos",
        onClick = { /* Acción para "Movimientos" */ }
    ),
    BottomBarItem(
        icon = Icons.Outlined.MailOutline,
        text = "Tarjetas",
        onClick = { /* Acción para "Tarjetas" */ }
    ),
    BottomBarItem(
        icon = Icons.Outlined.AccountCircle,
        text = "Cuenta",
        onClick = { /* Acción para "Cuenta" */ }
    )
)


@Composable
fun BottomBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.tertiary,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
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
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.text,
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
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Icono de inicio",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 8.dp)
                        .size(40.dp)
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

val homeItems = listOf(
    BottomBarItem(
        icon = Icons.Outlined.Home,
        text = "Transferir",
        onClick = { /* Acción para "Transferir" */ }
    ),
    BottomBarItem(
        icon = Icons.Outlined.DateRange,
        text = "Ingresar",
        onClick = { /* Acción para "Ingresar" */ }
    ),
    BottomBarItem(
        icon = Icons.Outlined.MailOutline,
        text = "Tarjetas",
        onClick = { /* Acción para "Tarjetas" */ }
    ),
    BottomBarItem(
        icon = Icons.Outlined.AccountCircle,
        text = "Cobrar",
        onClick = { /* Acción para "Cobrar" */ }
    )
)

@Composable
fun SaldoSection() {
    Surface (
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(35.dp),
        modifier = Modifier.padding(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(16.dp)
        ) {
            Text(
                text = "Hola, Samanta!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Tu saldo actual es",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = "$204.129,00",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(10.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
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
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.text,
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
        CardExample(
            bank = "Santander",
            number = "1234 5678 9101 1121",
            name = "Samanta Jones",
            date = "12/28",
        )
    }
}

@Composable
fun CardExample(bank: String, number: String, name: String, date: String){
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(10.dp)
    ){
        Column (modifier = Modifier.size(width = 250.dp, height = 160.dp)){
            Text(
                text = bank,
                modifier = Modifier.align(Alignment.End).padding(horizontal = 5.dp, vertical = 5.dp)
            )
            Text(
                text = number,
                modifier = Modifier.align(Alignment.Start).padding(horizontal = 10.dp, vertical = 10.dp)
            )
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround) {
                Text(
                    text = name,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
                )
                Text(
                    text = date,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 10.dp)
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
        Text(
            text = "Movimientos",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )

        MovimientoItem(
            name = "Steam",
            date = "ayer 16:20",
            amount = "-$120.69",
            color = Color.Red
        )
    }
}

@Composable
fun MovimientoItem(name: String, date: String, amount: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Text(
                text = date,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
        Text(
            text = amount,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}



