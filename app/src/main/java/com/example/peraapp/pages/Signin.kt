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
import androidx.compose.material.icons.filled.*
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
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.peraapp.HomeViewModel
import com.example.peraapp.PeraApplication
import com.example.peraapp.data.model.RegisterUser
import com.example.peraapp.data.model.User
import java.util.Date

@Composable
fun SigninScreen(onNavigateToRoute: (String) -> Unit,
                 viewModel: HomeViewModel) {
    ModularizedLayout(
        contentPhonePortrait = { SigninScreenPhonePortrait(onNavigateToRoute, viewModel) },
        contentPhoneLandscape = { SigninScreenPhoneLandscape(onNavigateToRoute, viewModel) },
        contentTabletPortrait = { SigninScreenTabletPortrait(onNavigateToRoute, viewModel) },
        contentTabletLandscape = { SigninScreenTabletLandscape(onNavigateToRoute, viewModel) }
    )
}


@Composable
fun SigninScreenPhoneLandscape(onNavigateToRoute: (String) -> Unit,
                               viewModel: HomeViewModel) {
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
                modifier = Modifier.size(width = 600.dp, height = 480.dp),
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun SigninScreenTabletPortrait(onNavigateToRoute: (String) -> Unit,
                               viewModel: HomeViewModel) {
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
                FormFields(onNavigateToRoute, viewModel)
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
fun FormContainer(onNavigateToRoute: (String) -> Unit, modifier: Modifier, viewModel: HomeViewModel) {
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
            FormFields(onNavigateToRoute, viewModel)
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
fun FormFields(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel) {
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    var doPasswordsMatch by remember { mutableStateOf(true) }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.width(400.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            )
            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text("Surname") },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            )
        }

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            },
            label = { Text("Email") },
            modifier = Modifier
                .width(400.dp)
                .height(60.dp),
            isError = !isEmailValid,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        )

        if (!isEmailValid) {
            Text(
                text = "Invalid Email",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                doPasswordsMatch = password == confirmPassword
            },
            label = { Text("Password") },
            modifier = Modifier
                .width(400.dp)
                .height(60.dp),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (isPasswordVisible) "Hide" else "Show"
                    )
                }
            }
        )

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                doPasswordsMatch = password == confirmPassword
            },
            label = { Text("Confirm Password") },
            modifier = Modifier
                .width(400.dp)
                .height(60.dp),
            visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }) {
                    Icon(
                        imageVector = if (isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (isConfirmPasswordVisible) "Hide" else "Show"
                    )
                }
            }
        )

        if (!doPasswordsMatch) {
            Text(
                text = "Passwords do not match",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        FormActions(onNavigateToRoute, name, surname, email, password, viewModel)
    }

}

@Composable
fun FormActions(
    onNavigateToRoute: (String) -> Unit,
    name: String,
    surname: String,
    email: String,
    password: String,
    viewModel: HomeViewModel

) {
    val uiState by viewModel.uiState.collectAsState()
    var showStateDialog by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(false) }

    Button(
        onClick =
        {
            viewModel.signin(RegisterUser(
                firstName = name,
                lastName = surname,
                birthDate = "2000-05-12",
                email = email,
                password = password
            ))
            state = uiState.error == null
            showStateDialog = true
        },
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

    if(showStateDialog) {
        SigninDialog(
            onDismissRequest = { showStateDialog = false },
            state = state
        )
    }
}

@Composable
fun SigninScreenPhonePortrait(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
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
                FormFields(onNavigateToRoute, viewModel)
            }
        }
    }
}

@Composable
fun SigninScreenTabletLandscape(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel) {
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
                modifier = Modifier.size(width = 600.dp, height = 480.dp),
                viewModel = viewModel
            )
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
    dialogTitle: String = stringResource(R.string.registrarme),
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
