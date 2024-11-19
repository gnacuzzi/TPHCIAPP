package com.example.peraapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.TopBar
import com.example.peraapp.ui.theme.PeraAppTheme
import com.example.peraapp.components.isLandscape
import com.example.peraapp.components.isTablet
import com.example.peraapp.components.BackButton

@Composable
fun DepositPage(onNavigateToRoute: (String) -> Unit){
    val configuration = LocalConfiguration.current


    val isTablet = isTablet(configuration)
    val isLandscape = isLandscape(configuration)

    if (isTablet) {
        if(isLandscape){
            DepositPageTabletLandscape(onNavigateToRoute)
        }else{
            DepositPageTabletPortrait(onNavigateToRoute)
        }
    } else {
        if(isLandscape){
            DepositPagePhoneLandscape(onNavigateToRoute)
        }else{
            DepositPagePhonePortrait(onNavigateToRoute)
        }
    }

}

@Composable
fun DepositPageTabletLandscape(onNavigateToRoute: (String) -> Unit){
    var amount by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(R.string.ingresar),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 20.dp).align(Alignment.Start)
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            BackButton(onNavigateToRoute)

            Row (
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {

                DepositInputField(
                    labelText = stringResource(R.string.ingresarmonto),
                    value = amount,
                    onValueChange = { amount = it },
                    keyboardType = KeyboardType.Number,
                    width = 200
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "${stringResource(R.string.con)}:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                )

                LazyRow {//foreach
                    item { CardTablet(name = "Samanta Jones", bank = "Santander", number = "1234 1111 5678 2212", date = "12/28") { } }
                }

                DepositButton {  }
            }
        }
    }
}

@Composable
fun DepositPageTabletPortrait(onNavigateToRoute: (String) -> Unit){
    var amount by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(R.string.ingresar),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 20.dp).align(Alignment.Start)
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            BackButton(onNavigateToRoute)

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                DepositInputField(
                    labelText = stringResource(R.string.ingresarmonto),
                    value = amount,
                    onValueChange = { amount = it },
                    keyboardType = KeyboardType.Number,
                    width = 200
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp)
            ){
                Text(
                    text = "${stringResource(R.string.con)}:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                )

                LazyRow {//foreach
                    item { CardTablet(name = "Samanta Jones", bank = "Santander", number = "1234 1111 5678 2212", date = "12/28") { } }
                }

                DepositButton {  }
            }
        }
    }
}

@Composable
fun DepositPagePhoneLandscape(onNavigateToRoute: (String) -> Unit){
    var amount by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopBar(R.string.ingresar, false) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BackButton(onNavigateToRoute)
            Row (
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    DepositInputField(
                        labelText = stringResource(R.string.ingresarmonto),
                        value = amount,
                        onValueChange = { amount = it },
                        keyboardType = KeyboardType.Number,
                        width = 200
                    )
                }
                LazyColumn{//foreach
                    item {
                        CardHome(
                            name = "Samanta Jones",
                            bank = "Santander",
                            number = "1234 1111 5678 2212",
                            date = "12/28"
                        ) { }
                    }
                }
                DepositButton(
                    onClick = { /* Acción para transferir */ }
                )
            }
        }
    }
}
@Composable
fun DepositPagePhonePortrait(onNavigateToRoute: (String) -> Unit){
    var amount by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopBar(R.string.ingresar) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BackButton(onNavigateToRoute)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                DepositInputField(
                    labelText = stringResource(R.string.ingresarmonto),
                    value = amount,
                    onValueChange = { amount = it },
                    keyboardType = KeyboardType.Number
                )

                LazyRow{//foreach
                    item { Card(name = "Samanta Jones", bank = "Santander", number = "1234 1111 5678 2212", date = "12/28") { } }
                }


                DepositButton(
                    onClick = { /* Acción para transferir */ }
                )
            }
        }
    }
}


@Composable
fun DepositInputField(
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    width: Int = 270
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = labelText,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(top = 8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(labelText) },
            modifier = Modifier
                .padding(bottom = 10.dp, top = 20.dp)
                .width(width.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType
            ),
        )
    }
}


@Composable
fun DepositButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(top = 20.dp)
            .width(200.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(stringResource(R.string.ingresar), style = MaterialTheme.typography.titleLarge)
    }
}


@PreviewSizes
@Composable
fun DepositPagePreview() {
    PeraAppTheme{
        DepositPage{}
    }
}