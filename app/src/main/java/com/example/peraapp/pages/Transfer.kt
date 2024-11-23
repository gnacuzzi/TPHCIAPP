package com.example.peraapp.pages

import androidx.compose.foundation.layout.Arrangement
import com.example.peraapp.R
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.HomeViewModel
import com.example.peraapp.components.TopBar
import com.example.peraapp.components.ModularizedLayout
import com.example.peraapp.data.model.BalancePayment
import com.example.peraapp.data.model.CardPayment
import com.example.peraapp.navigation.AppDestinations
import kotlinx.coroutines.delay

@Composable
fun TransferScreen(onNavigateToRoute: (String) -> Unit,
                   viewModel: HomeViewModel
) {
    ModularizedLayout(
        contentPhonePortrait = { TransferScreenPhonePortrait(onNavigateToRoute, viewModel) },
        contentPhoneLandscape = { TransferScreenPhoneLandscape(onNavigateToRoute, viewModel) },
        contentTabletPortrait = { TransferScreenTabletPortrait(onNavigateToRoute, viewModel) },
        contentTabletLandscape = { TransferScreenTabletLandscape(onNavigateToRoute, viewModel) }
    )
}


@Composable
fun TransferScreenTabletLandscape(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel){
    var mail by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val cards = uiState.cards ?: emptyList()
    var method by remember { mutableStateOf<String?>(null) }
    var cardId by remember { mutableStateOf(0) }
    var state by remember { mutableStateOf(false) }
    var selectedCardId by remember { mutableStateOf<Int?>(null) }

    val isButtonEnabled = remember(mail, amount, method) {
        mail.isNotEmpty() && isValidEmail(mail) &&
                amount.isNotEmpty() && amount.toFloatOrNull()?.let { it > 0 } == true &&
                method != null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(R.string.transferir),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .align(Alignment.Start)
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
                TransferInputField(
                    labelText = stringResource(R.string.mailpera),
                    value = mail,
                    onValueChange = { mail = it },
                    keyboardType = KeyboardType.Email,
                    width = 200
                )

                TransferInputField(
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
                    text = "${stringResource(R.string.formadepago)}:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                )

                LazyRow {
                    item {
                        CardTablet(
                            bank = "Pera",
                            number = uiState.walletDetail?.balance.toString(),
                            name = stringResource(R.string.saldoencuenta),
                            date = "",
                            isSelected = selectedCardId == null,
                            onCardClick = {
                                method = "BALANCE"
                                state = true
                                selectedCardId = null
                            }
                        )
                    }

                    items(cards.size) { index ->
                        val card = cards[index]
                        CardTablet(
                            bank = card.type.name,
                            number = card.number,
                            name = card.fullName,
                            date = card.expirationDate,
                            isSelected = selectedCardId == card.id,
                            onCardClick = {
                                method = "CARD"
                                cardId = card.id!!
                                state = true
                                selectedCardId = card.id
                            }
                        )
                    }
                }

                if(isButtonEnabled) {
                    TransferButton(
                        onNavigateToRoute = onNavigateToRoute,
                        onClick = {  },
                        email = mail,
                        amount = amount,
                        method = method,
                        cardId = cardId,
                        viewModel = viewModel
                    )
                }

            }
        }
    }
}

@Composable
fun TransferScreenTabletPortrait(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel){
    var mail by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val cards = uiState.cards ?: emptyList()
    var method by remember { mutableStateOf<String?>(null) }
    var cardId by remember { mutableStateOf(0) }
    var state by remember { mutableStateOf(false) }
    var selectedCardId by remember { mutableStateOf<Int?>(null) }

    val isButtonEnabled = remember(mail, amount, method) {
        mail.isNotEmpty() && isValidEmail(mail) &&
                amount.isNotEmpty() && amount.toFloatOrNull()?.let { it > 0 } == true &&
                method != null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        Text(
            text = stringResource(R.string.transferir),
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .align(Alignment.Start)
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
                TransferInputField(
                    labelText = stringResource(R.string.mailpera),
                    value = mail,
                    onValueChange = { mail = it },
                    keyboardType = KeyboardType.Email,
                    width = 200
                )

                TransferInputField(
                    labelText = stringResource(R.string.ingresarmonto),
                    value = amount,
                    onValueChange = { amount = it },
                    keyboardType = KeyboardType.Number,
                    width = 200
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
            ){
                Text(
                    text = "${stringResource(R.string.formadepago)}:",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                )

                LazyRow {
                    item {
                        CardTablet(
                            bank = "Pera",
                            number = uiState.walletDetail?.balance.toString(),
                            name = stringResource(R.string.saldoencuenta),
                            date = "",
                            isSelected = selectedCardId == null,
                            onCardClick = {
                                method = "BALANCE"
                                state = true
                                selectedCardId = null
                            }
                        )
                    }

                    items(cards.size) { index ->
                        val card = cards[index]
                        CardTablet(
                            bank = card.type.name,
                            number = card.number,
                            name = card.fullName,
                            date = card.expirationDate,
                            isSelected = selectedCardId == card.id,
                            onCardClick = {
                                method = "CARD"
                                cardId = card.id!!
                                state = true
                                selectedCardId = card.id
                            }
                        )
                    }
                }

                if(isButtonEnabled) {
                    TransferButton(
                        onNavigateToRoute = onNavigateToRoute,
                        onClick = { },
                        email = mail,
                        amount = amount,
                        method = method,
                        cardId = cardId,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun TransferScreenPhoneLandscape(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel){
    var mail by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val cards = uiState.cards ?: emptyList()
    var method by remember { mutableStateOf<String?>(null) }
    var cardId by remember { mutableStateOf(0) }
    var state by remember { mutableStateOf(false) }
    var selectedCardId by remember { mutableStateOf<Int?>(null) }

    val isButtonEnabled = remember(mail, amount, method) {
        mail.isNotEmpty() && isValidEmail(mail) &&
                amount.isNotEmpty() && amount.toFloatOrNull()?.let { it > 0 } == true &&
                method != null
    }

    Scaffold(
        topBar = { TopBar(R.string.transferir, false) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row (
                modifier = Modifier.fillMaxSize(),
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TransferInputField(
                        labelText = stringResource(R.string.mailpera),
                        value = mail,
                        onValueChange = { mail = it },
                        keyboardType = KeyboardType.Email,
                        width = 200
                    )

                    TransferInputField(
                        labelText = stringResource(R.string.ingresarmonto),
                        value = amount,
                        onValueChange = { amount = it },
                        keyboardType = KeyboardType.Number,
                        width = 200
                    )
                }
                LazyColumn {
                    item {
                        CardHome(
                            bank = "Pera",
                            number = uiState.walletDetail?.balance.toString(),
                            name = stringResource(R.string.saldoencuenta),
                            date = "",
                            isSelected = selectedCardId == null,
                            onCardClick = {
                                method = "BALANCE"
                                state = true
                                selectedCardId = null
                            }
                        )
                    }

                    items(cards.size) { index ->
                        val card = cards[index]
                        CardHome(
                            bank = card.type.name,
                            number = card.number,
                            name = card.fullName,
                            date = card.expirationDate,
                            isSelected = selectedCardId == card.id,
                            onCardClick = {
                                method = "CARD"
                                cardId = card.id!!
                                state = true
                                selectedCardId = card.id
                            }
                        )
                    }
                }

                if(isButtonEnabled) {
                    TransferButton(
                        onNavigateToRoute = onNavigateToRoute,
                        onClick = { },
                        email = mail,
                        amount = amount,
                        method = method,
                        cardId = cardId,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun TransferScreenPhonePortrait(
    onNavigateToRoute: (String) -> Unit,
    viewModel: HomeViewModel
) {
    var mail by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val cards = uiState.cards ?: emptyList()
    var method by remember { mutableStateOf<String?>(null) }
    var cardId by remember { mutableStateOf(0) }
    var selectedCardId by remember { mutableStateOf<Int?>(null) }

    val isButtonEnabled = remember(mail, amount, method) {
        mail.isNotEmpty() && isValidEmail(mail) &&
                amount.isNotEmpty() && amount.toFloatOrNull()?.let { it > 0 } == true &&
                method != null
    }

    Scaffold(
        topBar = { TopBar(R.string.transferir) }
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
                TransferInputField(
                    labelText = stringResource(R.string.mailpera),
                    value = mail,
                    onValueChange = { mail = it },
                    keyboardType = KeyboardType.Email
                )

                TransferInputField(
                    labelText = stringResource(R.string.ingresarmonto),
                    value = amount,
                    onValueChange = { amount = it },
                    keyboardType = KeyboardType.Number
                )

                LazyRow {
                    item {
                        Card(
                            bank = "Pera",
                            number = uiState.walletDetail?.balance.toString(),
                            name = stringResource(R.string.saldoencuenta),
                            date = "",
                            isSelected = selectedCardId == null,
                            onCardClick = {
                                method = "BALANCE"
                                selectedCardId = null
                            }
                        )
                    }

                    items(cards.size) { index ->
                        val card = cards[index]
                        Card(
                            bank = card.type.name,
                            number = card.number,
                            name = card.fullName,
                            date = card.expirationDate,
                            isSelected = selectedCardId == card.id,
                            onCardClick = {
                                method = "CARD"
                                cardId = card.id!!
                                selectedCardId = card.id
                            }
                        )
                    }
                }

                if (isButtonEnabled) {
                    TransferButton(
                        onNavigateToRoute = onNavigateToRoute,
                        onClick = {  },
                        email = mail,
                        amount = amount,
                        method = method,
                        cardId = cardId,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}


@Composable
fun TransferInputField(
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier,
    width: Int = 270,
    showError: Boolean = false
) {
    var error by remember { mutableStateOf(false) }
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
            onValueChange = {
                if (keyboardType == KeyboardType.Email) {
                    error = !isValidEmail(it)
                } else if (keyboardType == KeyboardType.Number) {
                    error = !(it.isEmpty() || it.matches("\\d*".toRegex()))
                }
                onValueChange(it)
            },
            label = { Text(labelText) },
            modifier = Modifier
                .padding(bottom = 10.dp, top = 20.dp)
                .width(width.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType
            ),
            isError = showError || error,
            supportingText = {
                if (showError || error) {
                    if (keyboardType == KeyboardType.Email) {
                        Text(
                            text = stringResource(R.string.email_invalido),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    } else if (keyboardType == KeyboardType.Number) {
                        Text(
                            text = stringResource(R.string.monto_invalido),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun TransferButton(
    onNavigateToRoute: (String) -> Unit,
    onClick: () -> Unit, email: String,
    amount: String, method: String?, cardId: Int, viewModel: HomeViewModel) {
    val description = stringResource(R.string.transferencia)

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        if (method != null) {
            TransferDialog(
                onNavigateToRoute = onNavigateToRoute,
                onDismissRequest = { showDialog = false },
                dialogTitle = stringResource(R.string.transferir),
                recipientEmail = email,
                amount = amount.toInt(),
                method = method,
                viewModel = viewModel,
                cardId = cardId,
            )
        }
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
        Text(stringResource(R.string.transferir), style = MaterialTheme.typography.titleLarge)
    }
}


@Composable
fun TransferDialog(
    onNavigateToRoute: (String) -> Unit,
    onDismissRequest: () -> Unit,
    dialogTitle: String,
    recipientEmail: String,
    amount: Int,
    method: String,
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
                Text("${stringResource(R.string.pagoa)}: $recipientEmail")
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
                        if (method == "BALANCE") {
                            val balance = BalancePayment(
                                amount = amount,
                                description = R.string.transferencia.toString(),
                                receiverEmail = recipientEmail,
                                type = "BALANCE"
                            )
                            viewModel.makeBalancePayment(balance)
                            state = uiState.error == null
                        } else if (method == "CARD") {
                            val card = CardPayment(
                                amount = amount,
                                description = R.string.transferencia.toString(),
                                cardId = cardId,
                                receiverEmail = recipientEmail,
                                type = "CARD"
                            )
                            viewModel.makeCardPayment(card)
                            state = uiState.error == null
                        }
                        showStateDialog = true
                    }) {
                        Text(text = stringResource(R.string.confirmar))
                    }

                }
            }
        }
    }
}


@Composable
fun TransferDialogState(
    onDismissRequest: () -> Unit,
    dialogTitle: String = stringResource(R.string.transferir),
    dismissAfterMillis: Long = 2000,
    state: Boolean = true
) {
    var addText = stringResource(R.string.fallo)
    if (state){
        addText = stringResource(R.string.correcto)
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