package com.example.peraapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.ModularizedLayout
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.ui.theme.PeraAppTheme
import kotlinx.coroutines.delay

@Composable
fun SigninScreen(onNavigateToRoute: (String) -> Unit) {
    ModularizedLayout(
        contentPhonePortrait = { SigninScreenPhonePortrait(onNavigateToRoute) },
        contentPhoneLandscape = { SigninScreenPhoneLandscape(onNavigateToRoute) },
        contentTabletPortrait = { SigninScreenTabletPortrait(onNavigateToRoute) },
        contentTabletLandscape = { SigninScreenTabletLandscape(onNavigateToRoute) }
    )
}


@Composable
fun SigninScreenPhoneLandscape(onNavigateToRoute: (String) -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TopSectionImage(
                imageResId = R.drawable.fotoiniciotablet,
                contentDescription = stringResource(R.string.fotoinicio),
                modifier = Modifier.size(400.dp)
            )
            FormContainer(
                onNavigateToRoute = onNavigateToRoute,
                modifier = Modifier.size(width = 600.dp, height = 480.dp)
            )
        }
    }
}

@Composable
fun SigninScreenTabletPortrait(onNavigateToRoute: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.63f)
                .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSectionImage(
                imageResId = R.drawable.fotoinicio,
                contentDescription = stringResource(R.string.fotoinicio),
                modifier = Modifier.size(600.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                FormHeader(onNavigateToRoute)
            }
            item {
                FormFields()
            }
            item {
                FormActions(onNavigateToRoute)
            }
        }
    }
}

@Composable
fun TopSectionImage(imageResId: Int, contentDescription: String, modifier: Modifier) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun FormContainer(onNavigateToRoute: (String) -> Unit, modifier: Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FormHeader(onNavigateToRoute)
            FormFields()
            FormActions(onNavigateToRoute)
        }
    }
}

@Composable
fun FormHeader(onNavigateToRoute: (String) -> Unit) {
    Row {
        IconButton(
            onClick = { onNavigateToRoute(AppDestinations.INICIARSESION.route) },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.volveratras),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Text(
            text = stringResource(R.string.comenza),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun FormFields() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.width(400.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = { Text(stringResource(R.string.name)) },
            modifier = Modifier.weight(1f).height(55.dp)
        )
        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = { Text(stringResource(R.string.apellido)) },
            modifier = Modifier.weight(1f).height(55.dp)
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.width(400.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = { Text(stringResource(R.string.dni)) },
            modifier = Modifier.weight(1f).height(55.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )
        OutlinedTextField(
            value = "",
            onValueChange = { /* Manejar el cambio de valor */ },
            label = {
                Text(
                    stringResource(R.string.fechadenacimiento),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            modifier = Modifier.weight(1f).height(55.dp),
            placeholder = { Text("DD/MM/AAAA") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )
    }
    OutlinedTextField(
        value = "",
        onValueChange = { /* Manejar el cambio de valor */ },
        label = { Text(stringResource(R.string.mail)) },
        modifier = Modifier.width(400.dp).height(55.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        )
    )
    OutlinedTextField(
        value = "",
        onValueChange = { /* Manejar el cambio de valor */ },
        label = { Text(stringResource(R.string.contraseña)) },
        modifier = Modifier.width(400.dp).height(55.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        )
    )
    OutlinedTextField(
        value = "",
        onValueChange = { /* Manejar el cambio de valor */ },
        label = { Text(stringResource(R.string.confirmarcontraseña)) },
        modifier = Modifier.width(400.dp).height(55.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        )
    )
}

@Composable
fun FormActions(onNavigateToRoute: (String) -> Unit) {
    Button(
        onClick = { onNavigateToRoute(AppDestinations.INICIARSESION.route) },
        modifier = Modifier
            .width(180.dp)
            .padding(vertical = 16.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.background,
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Text(stringResource(R.string.registrarme), style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun SigninScreenPhonePortrait(onNavigateToRoute: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.50f)
                .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSectionImage(
                imageResId = R.drawable.fotoinicio,
                contentDescription = stringResource(R.string.fotoinicio),
                modifier = Modifier.size(400.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                FormHeader(onNavigateToRoute)
            }
            item {
                FormFields()
            }
            item {
                FormActions(onNavigateToRoute)
            }
        }
    }
}

@Composable
fun SigninScreenTabletLandscape(onNavigateToRoute: (String) -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TopSectionImage(
                imageResId = R.drawable.fotoiniciotablet,
                contentDescription = stringResource(R.string.fotoinicio),
                modifier = Modifier.size(600.dp)
            )

            FormContainer(
                onNavigateToRoute = onNavigateToRoute,
                modifier = Modifier.size(width = 600.dp, height = 480.dp)
            )
        }
    }
}

@PreviewSizes
@Composable
fun SigninScreenPreview(){
    PeraAppTheme {
        SigninScreen{

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SigninDialogPreview() {
    PeraAppTheme {
        SigninDialog(
            onDismissRequest = { /* seria volver al inicio */ },
            dialogTitle = stringResource(R.string.estadoregistro)
        )
    }
}

@Composable
fun SigninDialog(
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
