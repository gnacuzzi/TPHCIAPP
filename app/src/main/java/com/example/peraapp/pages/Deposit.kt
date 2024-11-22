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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.HomeViewModel
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.TopBar
import com.example.peraapp.ui.theme.PeraAppTheme
import com.example.peraapp.components.ModularizedLayout
import com.example.peraapp.data.model.BalancePayment
import com.example.peraapp.data.model.CardPayment
import kotlinx.coroutines.delay

@Composable
fun DepositScreen(onNavigateToRoute: (String) -> Unit,
                  viewModel: HomeViewModel
){
    ModularizedLayout(
        contentPhonePortrait = { DepositScreenPhonePortrait(onNavigateToRoute, viewModel) },
        contentPhoneLandscape = { DepositScreenPhoneLandscape(onNavigateToRoute, viewModel) },
        contentTabletPortrait = { DepositScreenTabletPortrait(onNavigateToRoute, viewModel) },
        contentTabletLandscape = { DepositScreenTabletLandscape(onNavigateToRoute, viewModel) }
    )
}

@Composable
fun DepositScreenTabletLandscape(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel){
    val uiState by viewModel.uiState.collectAsState()
    var amount by remember { mutableStateOf("") }
    val cards = uiState.cards ?: emptyList()
    var method by remember { mutableStateOf<String?>(null) }
    var cardId by remember { mutableStateOf(0) }
    var state by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

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
                    items(cards.size) { index ->
                        val card = cards[index]
                        CardTablet(
                            bank = card.type.name,
                            number = card.number,
                            name = card.fullName,
                            date = card.expirationDate,
                            onCardClick = {
                                method = "CARD"
                                state = true
                                cardId = card.id!!
                                showDialog = true
                            }
                        )
                    }
                }

                DepositButton(
                    onClick = { /* Agregar lógica si es necesario */ },
                    amount = amount,
                    method = method,
                    cardId = cardId,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun DepositScreenTabletPortrait(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel){
    val uiState by viewModel.uiState.collectAsState()
    var amount by remember { mutableStateOf("") }
    val cards = uiState.cards ?: emptyList()
    var method by remember { mutableStateOf<String?>(null) }
    var cardId by remember { mutableStateOf(0) }
    var state by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

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
                    items(cards.size) { index ->
                        val card = cards[index]
                        CardTablet(
                            bank = card.type.name,
                            number = card.number,
                            name = card.fullName,
                            date = card.expirationDate,
                            onCardClick = {
                                method = "CARD"
                                state = true
                                cardId = card.id!!
                                showDialog = true
                            }
                        )
                    }
                }

                DepositButton(
                    onClick = { /* Agregar lógica si es necesario */ },
                    amount = amount,
                    method = method,
                    cardId = cardId,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun DepositScreenPhoneLandscape(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel){
    val uiState by viewModel.uiState.collectAsState()
    var amount by remember { mutableStateOf("") }
    val cards = uiState.cards ?: emptyList()
    var method by remember { mutableStateOf<String?>(null) }
    var cardId by remember { mutableStateOf(0) }
    var state by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar(R.string.ingresar, false) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
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
                    items(cards.size) { index ->
                        val card = cards[index]
                        CardHome(
                            bank = card.type.name,
                            number = card.number,
                            name = card.fullName,
                            date = card.expirationDate,
                            onCardClick = {
                                method = "CARD"
                                state = true
                                cardId = card.id!!
                                showDialog = true
                            }
                        )
                    }
                }
                DepositButton(
                    onClick = { /* Agregar lógica si es necesario */ },
                    amount = amount,
                    method = method,
                    cardId = cardId,
                    viewModel = viewModel
                )
            }
        }
    }
}
@Composable
fun DepositScreenPhonePortrait(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel){
    val uiState by viewModel.uiState.collectAsState()
    var amount by remember { mutableStateOf("") }
    val cards = uiState.cards ?: emptyList()
    var method by remember { mutableStateOf<String?>(null) }
    var cardId by remember { mutableStateOf(0) }
    var state by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar(R.string.ingresar) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
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
                    items(cards.size) { index ->
                        val card = cards[index]
                        Card(
                            bank = card.type.name,
                            number = card.number,
                            name = card.fullName,
                            date = card.expirationDate,
                            onCardClick = {
                                method = "CARD"
                                state = true
                                cardId = card.id!!
                                showDialog = true
                            }
                        )
                    }
                }


                DepositButton(
                    onClick = { /* Agregar lógica si es necesario */ },
                    amount = amount,
                    method = method,
                    cardId = cardId,
                    viewModel = viewModel
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
fun DepositButton(onClick: () -> Unit,
  amount: String, method: String?, cardId:Int , viewModel: HomeViewModel) {

    val description = stringResource(R.string.ingresar)

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DepositDialog(
            onDismissRequest = { showDialog = false },
            onConfirmation = {},
            dialogTitle = stringResource(R.string.ingresar),
            amount = amount.toInt(),
            method = method,
            viewModel = viewModel,
            cardId = cardId,
        )
    }

    Button(
        onClick = { showDialog = true },
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

@Composable
fun DepositDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    amount: Int,
    method: String?,
    cardId: Int,
    viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var showStateDialog by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(false) }
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
                Text("${stringResource(R.string.monto)}: $amount")
                Text("${stringResource(R.string.con)}: $method")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { onDismissRequest() }) {
                        Text(text = stringResource(R.string.cancelar))
                    }

                    Button(onClick = {
                        if (method == "CARD") {
                            val card = uiState.currentUser?.let {
                                CardPayment(
                                    amount = amount,
                                    description = R.string.ingresarmonto.toString(),
                                    cardId = cardId,
                                    receiverEmail = it.email,
                                    type = "CARD"
                                )
                            }
                            if (card != null) {
                                viewModel.makeCardPayment(card)
                            }
                            state = uiState.error == null
                        }
                        showStateDialog = true
                    }) {
                        Text(text = stringResource(R.string.confirmar))
                    }

                    if(showStateDialog) {
                        TransferDialogState(
                            onDismissRequest = {
                                showStateDialog = false
                                onDismissRequest()
                            },
                            dialogTitle = stringResource(R.string.ingresar),
                            state = state
                        )
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DepositDialogStatePreview() {
    PeraAppTheme {
        DepositDialogState(
            onDismissRequest = { /* seria eliminarse nomas */ },
            dialogTitle = stringResource(R.string.estadoingresar)
        )
    }
}

@Composable
fun DepositDialogState(
    onDismissRequest: () -> Unit,
    dialogTitle: String,
    dismissAfterMillis: Long = 2000,
    state: Boolean = true
) {
    var addText = stringResource(R.string.correcto)
    if (!state){
        addText = stringResource(R.string.fallo)
    }
    LaunchedEffect(Unit) {
        delay(dismissAfterMillis)
        onDismissRequest()
    }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
        ) {
            Text(
                text = "$dialogTitle $addText",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(16.dp)
            )
        }
    }


}
