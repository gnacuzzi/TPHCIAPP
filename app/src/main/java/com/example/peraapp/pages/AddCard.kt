package com.example.peraapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import com.example.peraapp.ui.theme.PeraAppTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import com.example.peraapp.R
import com.example.peraapp.components.TopBar
import com.example.peraapp.components.isLandscape
import kotlinx.coroutines.delay
import com.example.peraapp.HomeViewModel
import com.example.peraapp.data.model.Card
import com.example.peraapp.data.model.CardType
import com.example.peraapp.navigation.AppDestinations
import java.util.Calendar
import java.util.Date

data class CardValues(
    val cardNumber: String = "",
    val cardHolder: String = "",
    val expiryDate: String = "",
    val cvv: String = "",
    val bank: String = ""
)


@Composable
fun AddCard(onNavigateToRoute: (String) -> Unit,
            viewModel: HomeViewModel) {
    val configuration = LocalConfiguration.current
    val isLandscape = isLandscape(configuration)
    var cardValues by remember { mutableStateOf(CardValues()) }

    if (isLandscape) {
        AddCardLandscape(
            onNavigateToRoute = onNavigateToRoute,
            cardValues = cardValues,
            onValueChange = { cardValues = it },
            viewModel = viewModel
        )
    } else {
        AddCardPortrait(
            onNavigateToRoute = onNavigateToRoute,
            cardValues = cardValues,
            onValueChange = { cardValues = it },
            viewModel = viewModel
        )
    }
}


@Composable
fun AddCardLandscape(
    onNavigateToRoute: (String) -> Unit,
    cardValues: CardValues,
    onValueChange: (CardValues) -> Unit,
    viewModel: HomeViewModel
) {
    Scaffold(
        topBar = { TopBar(R.string.agregartarjeta, false) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    CardNumberField(value = cardValues.cardNumber) { newValue ->
                        onValueChange(cardValues.copy(cardNumber = newValue))
                    }
                    Row {
                        CardHolderField(value = cardValues.cardHolder) { newValue ->
                            onValueChange(cardValues.copy(cardHolder = newValue))
                        }
                        ExpiryDateField(value = cardValues.expiryDate) { newValue ->
                            onValueChange(cardValues.copy(expiryDate = newValue))
                        }
                    }
                    Row {
                        CVVField(value = cardValues.cvv) { newValue ->
                            onValueChange(cardValues.copy(cvv = newValue))
                        }
                        BankField(value = cardValues.bank) { newValue ->
                            onValueChange(cardValues.copy(bank = newValue))
                        }
                    }
                }
                AddCardButton(onNavigateToRoute, 60, cardValues, viewModel)
            }
        }
    }
}

@Composable
fun AddCardPortrait(
    onNavigateToRoute: (String) -> Unit,
    cardValues: CardValues,
    onValueChange: (CardValues) -> Unit,
    viewModel: HomeViewModel
) {
    Scaffold(
        topBar = { TopBar(R.string.agregartarjeta) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CardNumberField(value = cardValues.cardNumber) { newValue ->
                    onValueChange(cardValues.copy(cardNumber = newValue))
                }
                CardHolderField(value = cardValues.cardHolder) { newValue ->
                    onValueChange(cardValues.copy(cardHolder = newValue))
                }
                ExpiryDateField(value = cardValues.expiryDate) { newValue ->
                    onValueChange(cardValues.copy(expiryDate = newValue))
                }
                CVVField(value = cardValues.cvv) { newValue ->
                    onValueChange(cardValues.copy(cvv = newValue))
                }
                BankField(value = cardValues.bank) { newValue ->
                    onValueChange(cardValues.copy(bank = newValue))
                }
                AddCardButton(onNavigateToRoute, 10, cardValues, viewModel)
            }
        }
    }
}

@Composable
fun CardNumberField(value: String, onValueChange: (String) -> Unit) {
    var cardNumberTouched by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.length <= 19 && newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        label = { Text(stringResource(R.string.numerotarjeta)) },
        modifier = Modifier.padding(bottom = 10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        isError = cardNumberTouched && (value.length !in listOf(15, 16, 19))
    )
    if (cardNumberTouched && value.length !in listOf(15, 16, 19)) {
        Text(
            text = stringResource(R.string.error_numero_tarjeta),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }

    LaunchedEffect(value) {
        if (value.isNotEmpty()) {
            cardNumberTouched = true
        }
    }
}



@Composable
fun CardHolderField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.nombretitular)) },
        modifier = Modifier.padding(bottom = 10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        )
    )
}

@Composable
fun ExpiryDateField(value: String, onValueChange: (String) -> Unit) {
    var expiryDateTouched by remember { mutableStateOf(false) }
    val formattedValue = remember(value) {
        formatExpiryDate(value)
    }

    OutlinedTextField(
        value = formattedValue,
        onValueChange = { newValue ->
            if (newValue.length <= 5 && newValue.matches(Regex("^\\d{0,2}/?\\d{0,2}$"))) {
                onValueChange(newValue)
            }
        },
        label = { Text(stringResource(R.string.fechadeven)) },
        modifier = Modifier.padding(bottom = 10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        isError = expiryDateTouched && !isValidExpiryDate(formattedValue)
    )
    if (expiryDateTouched && !isValidExpiryDate(formattedValue)) {
        Text(
            text = stringResource(R.string.error_fecha),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }

    LaunchedEffect(value) {
        if (value.isNotEmpty()) {
            expiryDateTouched = true
        }
    }
}

private fun isValidExpiryDate(expiryDate: String): Boolean {
    val dateRegex = Regex("^\\d{2}/\\d{2}$")
    if (!expiryDate.matches(dateRegex)) return false

    val parts = expiryDate.split("/")
    val month = parts[0].toIntOrNull() ?: return false
    val year = parts[1].toIntOrNull() ?: return false

    if (month !in 1..12) return false

    val currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100
    val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1

    return (year > currentYear) || (year == currentYear && month >= currentMonth)
}


fun formatExpiryDate(input: String): String {
    val digitsOnly = input.filter { it.isDigit() }

    return when {
        digitsOnly.length <= 2 -> {
            digitsOnly
        }
        digitsOnly.length in 3..4 -> {
            "${digitsOnly.substring(0, 2)}/${digitsOnly.substring(2)}"
        }
        else -> {
            "${digitsOnly.substring(0, 2)}/${digitsOnly.substring(2, 4)}"
        }
    }
}


@Composable
fun CVVField(value: String, onValueChange: (String) -> Unit) {
    var cvvTouched by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.length <= 4 && newValue.all { it.isDigit() }) {
                onValueChange(newValue)
            }
        },
        label = { Text(stringResource(R.string.codigo)) },
        modifier = Modifier.padding(bottom = 10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        isError = cvvTouched && (value.length != 3 && value.length != 4)
    )
    if (cvvTouched && (value.length != 3 && value.length != 4)) {
        Text(
            text = stringResource(R.string.error_codigo),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }

    LaunchedEffect(value) {
        if (value.isNotEmpty()) {
            cvvTouched = true
        }
    }
}


@Composable
fun BankField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.banco)) },
        modifier = Modifier.padding(bottom = 10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text
        )
    )
}

@Composable
fun AddCardButton(onNavigateToRoute: (String) -> Unit,
                  toppadding: Int = 10,
                  cardValues: CardValues,
                  viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var showStateDialog by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(false) }
    val newCard = Card(
        id = null,
        number = cardValues.cardNumber,
        expirationDate = cardValues.expiryDate,
        fullName = cardValues.cardHolder,
        cvv = cardValues.cvv,
        type = CardType.DEBIT,
        createdAt = Date(),
        updatedAt = Date()
    )
    Button(
        onClick =
        {
            viewModel.addCard(newCard)
            state = uiState.error == null
            showStateDialog = true
        },
        modifier = Modifier
            .padding(top = toppadding.dp)
            .width(270.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(stringResource(R.string.agregartarjeta), style = MaterialTheme.typography.titleMedium)
    }
    if(showStateDialog) {
        AddCardDialogState(
            onDismissRequest =
            {
                showStateDialog = false
                if(state){
                    onNavigateToRoute(AppDestinations.TARJETAS.route)
                }
            },
            state = state
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddCardDialogStatePreview() {
    PeraAppTheme {
        AddCardDialogState()
    }
}

@Composable
fun AddCardDialogState(
    onDismissRequest: () -> Unit = {},
    dialogTitle: String = stringResource(R.string.estadoagregartarjeta),
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

@Composable
fun AddCardTabletDialog(
    onNavigateToRoute: (String) -> Unit,
    onDismissRequest: () -> Unit,
    viewModel: HomeViewModel
) {
    var cardValues by remember { mutableStateOf(CardValues()) }

    fun updateCardValues(newValues: CardValues) {
        cardValues = newValues
    }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.volveratras),
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(100.dp)
                    )
                }

                CardNumberField(value = cardValues.cardNumber) { newValue ->
                    updateCardValues(cardValues.copy(cardNumber = newValue))
                }
                CardHolderField(value = cardValues.cardHolder) { newValue ->
                    updateCardValues(cardValues.copy(cardHolder = newValue))
                }
                ExpiryDateField(value = cardValues.expiryDate) { newValue ->
                    updateCardValues(cardValues.copy(expiryDate = newValue))
                }
                CVVField(value = cardValues.cvv) { newValue ->
                    updateCardValues(cardValues.copy(cvv = newValue))
                }
                BankField(value = cardValues.bank) { newValue ->
                    updateCardValues(cardValues.copy(bank = newValue))
                }
                AddCardButton(onNavigateToRoute ,10, cardValues, viewModel)
            }

        }
    }
}
