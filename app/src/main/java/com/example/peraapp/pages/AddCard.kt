package com.example.peraapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import com.example.peraapp.ui.theme.PeraAppTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
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
import com.example.peraapp.components.BackButton
import com.example.peraapp.components.TopBar
import com.example.peraapp.components.isLandscape
import kotlinx.coroutines.delay

data class CardValues(
    val cardNumber: String = "",
    val cardHolder: String = "",
    val expiryDate: String = "",
    val cvv: String = "",
    val bank: String = ""
)


@Composable
fun AddCard(onNavigateToRoute: (String) -> Unit) {
    val configuration = LocalConfiguration.current
    val isLandscape = isLandscape(configuration)
    var cardValues by remember { mutableStateOf(CardValues()) }

    if (isLandscape) {
        AddCardLandscape(
            onNavigateToRoute = onNavigateToRoute,
            cardValues = cardValues,
            onValueChange = { cardValues = it }
        )
    } else {
        AddCardPortrait(
            onNavigateToRoute = onNavigateToRoute,
            cardValues = cardValues,
            onValueChange = { cardValues = it }
        )
    }
}


@Composable
fun AddCardLandscape(
    onNavigateToRoute: (String) -> Unit,
    cardValues: CardValues,
    onValueChange: (CardValues) -> Unit
) {
    Scaffold(
        topBar = { TopBar(R.string.agregartarjeta, false) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
        ) {
            BackButton(onNavigateToRoute)
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
                AddCardButton()
            }
        }
    }
}



@Composable
fun AddCardPortrait(
    onNavigateToRoute: (String) -> Unit,
    cardValues: CardValues,
    onValueChange: (CardValues) -> Unit
) {
    Scaffold(
        topBar = { TopBar(R.string.agregartarjeta) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
        ) {
            BackButton(onNavigateToRoute)
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
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
                AddCardButton(60)
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
        isError = cardNumberTouched && (value.length !in 16..19)
    )
    if (cardNumberTouched && value.length !in 16..19) {
        Text(
            text = stringResource(R.string.error_numero_tarjeta),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }

    // Detecta cuando el campo ha sido tocado
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
    var expiryDate by remember { mutableStateOf("") }
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
        label = { Text("Fecha de Vencimiento") },
        modifier = Modifier.padding(bottom = 10.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        isError = expiryDateTouched && !formattedValue.matches(Regex("^\\d{2}/\\d{2}$"))
    )
    if (expiryDateTouched && !formattedValue.matches(Regex("^\\d{2}/\\d{2}$"))) {
        Text(
            text = "Formato de fecha inválido. Debe ser MM/AA",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }

    // Detecta cuando el campo ha sido tocado
    LaunchedEffect(value) {
        if (value.isNotEmpty()) {
            expiryDateTouched = true
        }
    }
}

// Función para formatear la fecha automáticamente
fun formatExpiryDate(input: String): String {
    // Remueve todo lo que no sea un número
    val digitsOnly = input.filter { it.isDigit() }

    return when {
        digitsOnly.length <= 2 -> {
            // Agrega solo los dos primeros dígitos
            digitsOnly
        }
        digitsOnly.length in 3..4 -> {
            // Coloca la barra inclinada después de los dos primeros dígitos
            "${digitsOnly.substring(0, 2)}/${digitsOnly.substring(2)}"
        }
        else -> {
            // Si hay más de 4 dígitos, solo toma los primeros 4
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

    // Detecta cuando el campo ha sido tocado
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
fun AddCardButton(toppadding: Int = 10) {
    Button(
        onClick = { /* Acción para agregar tarjeta */ },
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
}



@Preview(device = "spec:width=411dp,height=891dp", showBackground = true, showSystemUi = true)
@Composable
fun CardScreenPortraitPreview() {
    PeraAppTheme {
        AddCard{}
    }
}
@Preview(device = "spec:width=891dp,height=411dp", showBackground = true, showSystemUi = true)
@Composable
fun CardScreenLandscapePreview() {
    PeraAppTheme {
        AddCard{}
    }
}

@Preview(showBackground = true)
@Composable
fun AddCardDialogStatePreview() {
    PeraAppTheme {
        AddCardDialogState(
            onDismissRequest = { /* seria eliminarse nomas */ },
            dialogTitle = stringResource(R.string.estadoagregartarjeta)
        )
    }
}

@Composable
fun AddCardDialogState(
    onDismissRequest: () -> Unit,
    dialogTitle: String,
    dismissAfterMillis: Long = 3000,
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


