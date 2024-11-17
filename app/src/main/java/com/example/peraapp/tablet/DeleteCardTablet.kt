package com.example.peraapp.tablet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.CardHome
import com.example.peraapp.ui.theme.PeraAppTheme

class DeleteCardTablet {
}

@Composable
fun DeleteCardPageTablet() {
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
                text = "Tarjeta",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(bottom = 20.dp).align(Alignment.Start)
            )
            Column (
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                //aca iria la tarjeta que pasas por parametro
                CardTablet(//esto deberia ser un foreach
                    bank = "Galicia",
                    number = "1234 1111 9101 1121",
                    name = "Samanta Jones",
                    date = "12/26"
                ){}
            }
        }
        Column(
            modifier = Modifier.weight(0.5f).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.tertiary,
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .width(400.dp)
                    .height(80.dp)
            ) {
                Text(
                    text = "Eliminar esta tarjeta",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_c")
@Composable
fun MovementsTabletPreview() {
    PeraAppTheme {
        MainScreenTablet(){
            DeleteCardPageTablet()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteCardTabletDialogPreview() {
    PeraAppTheme {
        DeleteCardTabletDialog(
            onDismissRequest = { },
            onConfirmation = { },
            dialogTitle = "¿Desea eliminar esta tarjeta?"
            //habria que pasar la tarjeta pero actualmente es una funcion no una clase
        )
    }
}

@Composable
fun DeleteCardTabletDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = dialogTitle,
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                //habria que mandarla como parametro
                CardHome(name = "Samanta Jones", bank = "Santander", number = "1234 1111 5678 2212", date = "12/28") {

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(
                            text = "Cancelar",
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    TextButton(onClick = { onConfirmation() }) {
                        Text(
                            text = "Confirmar",
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}
