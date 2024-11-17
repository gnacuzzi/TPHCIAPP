package com.example.peraapp.tablet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.CardHome
import com.example.peraapp.Deposit
import com.example.peraapp.MovimientoItem
import com.example.peraapp.R
import com.example.peraapp.ui.theme.PeraAppTheme
import kotlinx.coroutines.handleCoroutineException

class HomeTablet {
}
@Composable
fun MainScreenTablet(
    bodyContent: @Composable () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
        modifier = Modifier
            .fillMaxSize()
        ) {
            SideBarTablet()
            bodyContent()
        }
    }
}

@Composable
fun HomePageTablet() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight()
        ) {
            SaldoSectionTablet(
                name = "Samanta",
                saldo = 0
            )
            MovimientosSectionTablet()
        }
        Column(modifier = Modifier.weight(0.4f)) {
            TarjetasSectionTablet()
        }
    }
}



@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_c")
@Composable
fun MainScreenTabletPreview() {
    PeraAppTheme {
        MainScreenTablet(){
            HomePageTablet()
        }
    }
}

data class sideBarItem(
    val iconResId: Int,
    val text: String,
    val onClick: () -> Unit
)

val sideBarItemsTablet = listOf(
    sideBarItem(
        iconResId = R.drawable.home,
        text = "Inicio",
        onClick = { /* Acción para "Inicio" */ }
    ),
    sideBarItem(
        iconResId = R.drawable.movimientos,
        text = "Movimientos",
        onClick = { /* Acción para "Movimientos" */ }
    ),
    sideBarItem(
        iconResId = R.drawable.tarjetas,
        text = "Tarjetas",
        onClick = { /* Acción para "Tarjetas" */ }
    ),
    sideBarItem(
        iconResId = R.drawable.cuenta,
        text = "Cuenta",
        onClick = { /* Acción para "Cuenta" */ }
    )
)

@Composable
fun SideBarTablet() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(100.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo pera",
            modifier = Modifier.size(70.dp)
        )
        sideBarItemsTablet.forEach { item ->
            SideBarItem(item = item)
        }
    }
}

@Composable
fun SideBarItem(item: sideBarItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = item.onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val icon: Painter = painterResource(id = item.iconResId)
        Image(
            painter = icon,
            contentDescription = item.text,
            modifier = Modifier.size(50.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
        )
    }
}


data class HomeBarItemTablet(
    val iconResId: Int,
    val text: String,
    val onClick: () -> Unit
)

val homeItemsTablet = listOf(
    HomeBarItemTablet(
        iconResId = R.drawable.transferir,
        text = "Transferir",
        onClick = { /* Acción para "Transferir" */ }
    ),
    HomeBarItemTablet(
        iconResId = R.drawable.ingresar,
        text = "Ingresar",
        onClick = { /* Acción para "Ingresar" */ }
    ),
    HomeBarItemTablet(
        iconResId = R.drawable.cobrar,
        text = "Cobrar",
        onClick = { /* Acción para "Cobrar" */ }
    ),
    HomeBarItemTablet(
        iconResId = R.drawable.invest,
        text = "Invertir",
        onClick = { /* Acción para "Cobrar" */ }
    )
)

@Composable
fun SaldoSectionTablet(name:String, saldo: Number) {
    Surface (
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
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
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                homeItemsTablet.forEach { item ->
                    Button(
                        onClick = item.onClick,
                        colors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.secondary,
                            containerColor = MaterialTheme.colorScheme.surface
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
                                modifier = Modifier.padding(top = 4.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TarjetasSectionTablet() {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(10.dp).fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Tarjetas",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CardHomeTablet(
                    bank = "Santander",
                    number = "1234 5678 9101 1121",
                    name = "Samanta Jones",
                    date = "12/28",
                ) {}

                CardHomeTablet(
                    bank = "BBVA",
                    number = "4321 8765 1011 2233",
                    name = "Juan Pérez",
                    date = "01/25",
                ) {}

                IconButton(
                    onClick = { },
                    modifier = Modifier.size(65.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Agregar tarjeta",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(65.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun MovimientosSectionTablet() {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(10.dp).fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Movimientos",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
                TextButton(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        text = "Ver más",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                MovimientoItemTablet(//cuando pongan lo del foreach no se olviden del arrangement
                    name = "Steam",
                    date = "ayer 16:20",
                    amount = "-$120.69",
                    color = Color.Red,
                    arrangement = Arrangement.SpaceBetween
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChargeDialogPreview() {
    PeraAppTheme {
        ChargeDialog(
            onDismissRequest = { },
            onConfirmation = { },
            dialogTitle = "Cobrar"
            //habria que pasar el monto
        )
    }
}

@Composable
fun ChargeDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            modifier = Modifier.width(800.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    IconButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    Text(
                        text = dialogTitle,
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterVertically)
                    )
                }
                Text(
                    text = "Ingresa el monto:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text("Monto") },
                    modifier = Modifier
                        .padding(bottom = 10.dp, top = 20.dp)
                        .align(Alignment.CenterHorizontally),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    textStyle = MaterialTheme.typography.titleLarge
                )

                Button(
                    onClick = { /* Acción para transferir */ },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .width(270.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Generar link", style = MaterialTheme.typography.titleLarge)
                }

                Text(
                    text = "Link generado: https:/aparece..cuando..apretas",
                    modifier = Modifier.padding(top = 20.dp),
                    style = MaterialTheme.typography.titleMedium
                )

                Button(//deberia solo aparecer una vez que apretas generar link
                    onClick = { onConfirmation() },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .width(270.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Compartir", style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DepositDialogPreview() {
    PeraAppTheme {
        DepositDialog(
            onDismissRequest = { },
            onConfirmation = { },
            dialogTitle = "Ingresar"
            //habria que pasar el monto
        )
    }
}

@Composable
fun DepositDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            modifier = Modifier.width(800.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás",
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                    Text(
                        text = dialogTitle,
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                Text(
                    text = "Tu CBU",
                    modifier = Modifier.padding(start = 10.dp, bottom = 20.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "00000121213242434354545", //hacerlo responsivo
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .border(1.dp, MaterialTheme.colorScheme.tertiary)
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = "Tu Alias",
                    modifier = Modifier.padding(start = 10.dp, bottom = 20.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "pera.app", //hacerlo responsivo
                    modifier = Modifier
                        .border(1.dp, MaterialTheme.colorScheme.tertiary)
                        .padding(8.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                Button(//deberia solo aparecer una vez que apretas generar link
                    onClick = { onConfirmation() },
                    modifier = Modifier
                        .padding(top = 60.dp)
                        .width(270.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Compartir datos", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}


