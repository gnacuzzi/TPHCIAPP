package com.example.peraapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.TopBar
import com.example.peraapp.components.TopBarTablet
import com.example.peraapp.ui.theme.PeraAppTheme

@Composable
fun InvestPage(name: Int, bodycontent: @Composable () -> Unit){
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet){
        Scaffold(
            topBar = { TopBarTablet(name) },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                bodycontent()
            }
        }
    }else{
        Scaffold(
            topBar = { TopBar(name) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                bodycontent()
            }
        }
    }


}

@Composable
fun InvestContent(){
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    if (isTablet){
        InvestContentTablet()
    }else{
        InvestContentPhone()
    }
}

@Composable
fun InvestContentTablet() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        IconButton(
            onClick = { /* Aquí iría la acción para volver atrás */ },
            modifier = Modifier.padding(bottom = 16.dp, start = 20.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.volveratras),
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(100.dp)
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp)
        ){
            Text(
                text = stringResource(R.string.rescatardinero),
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Text(
                    text = "$4000", //plata a rescatar, mandar como parametro
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(top = 20.dp)
                )

                Button(
                    onClick = { /* Acción para rescatar */ },
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .width(270.dp)
                        .align(Alignment.CenterVertically),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(stringResource(R.string.rescatar), style = MaterialTheme.typography.displaySmall)
                }
            }
            Text(
                text = "${stringResource(R.string.ingresainvertir)}:",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(top = 100.dp)
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Manejar el cambio de valor */ },
                    label = { Text(stringResource(R.string.monto)) },
                    modifier = Modifier
                        .padding(bottom = 10.dp, top = 20.dp, start = 30.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    textStyle = MaterialTheme.typography.titleLarge
                )

                Button(
                    onClick = { /* Acción para invertir */ },
                    modifier = Modifier
                        .padding(top = 40.dp, end = 50.dp)
                        .width(270.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(stringResource(R.string.invertir), style = MaterialTheme.typography.displaySmall)
                }
            }
        }
    }
}

@Composable
fun InvestContentPhone() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = { /* Aquí iría la acción para volver atrás */ },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.volveratras),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            text = stringResource(R.string.rescatardinero),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "$4000", //plata a rescatar, mandar como parametro
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        Button(
            onClick = { /* Acción para rescatar */ },
            modifier = Modifier
                .padding(top = 40.dp)
                .width(270.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.secondary,
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(stringResource(R.string.rescatar), style = MaterialTheme.typography.titleMedium)
        }

        Text(
            text = "${stringResource(R.string.ingresainvertir)}:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 30.dp)
        )

        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = { Text(stringResource(R.string.monto)) },
            modifier = Modifier
                .padding(bottom = 10.dp, top = 20.dp)
                .align(Alignment.CenterHorizontally),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            textStyle = MaterialTheme.typography.titleLarge
        )

        Button(
            onClick = { /* Acción para invertir */ },
            modifier = Modifier
                .padding(top = 40.dp)
                .width(270.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.secondary,
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(stringResource(R.string.invertir), style = MaterialTheme.typography.titleMedium)
        }

    }
}


@PreviewSizes
@Composable
fun InvestPagePreview() {
    PeraAppTheme {
        InvestPage (R.string.invertirrescate) {
            InvestContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InvestDialogPreview() {
    PeraAppTheme {
        InvestDialog(
            onDismissRequest = { },
            onConfirmation = { },
            dialogTitle = "${stringResource(R.string.deseainvertir)}?"
            //habria que pasar el monto
        )
    }
}

@Composable
fun InvestDialog(
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
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                //habria que hacer una transaccion y mandarla como parametro
                Text("${stringResource(R.string.monto)}: $200")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(
                            text = stringResource(R.string.cancelar),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    TextButton(onClick = { onConfirmation() }) {
                        Text(
                            text = stringResource(R.string.confirmar),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TakeBackDialogPreview() {
    PeraAppTheme {
        TakeBackDialog(
            onDismissRequest = { },
            onConfirmation = { },
            dialogTitle = "${stringResource(R.string.desearescatar)}?"
            //habria que pasar el monto
        )
    }
}

@Composable
fun TakeBackDialog(
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
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                //habria que hacer una transaccion y mandarla como parametro
                Text("${stringResource(R.string.monto)}: $200")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(
                            text = stringResource(R.string.cancelar),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    TextButton(onClick = { onConfirmation() }) {
                        Text(
                            text = stringResource(R.string.confirmar),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}


