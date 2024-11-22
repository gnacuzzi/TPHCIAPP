package com.example.peraapp.pages

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.components.ModularizedLayout
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.ui.theme.PeraAppTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.peraapp.HomeViewModel
import com.example.peraapp.PeraApplication
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(onNavigateToRoute: (String) -> Unit,
                viewModel: HomeViewModel ) {
    ModularizedLayout(
        contentPhonePortrait = { LoginScreenPhonePortrait(onNavigateToRoute, viewModel) },
        contentPhoneLandscape = { LoginScreenPhoneLandscape(onNavigateToRoute, viewModel) },
        contentTabletPortrait = { LoginScreenTabletPortrait(onNavigateToRoute, viewModel) },
        contentTabletLandscape = { LoginScreenTabletLandscape(onNavigateToRoute, viewModel) }
    )
}


@Composable
fun LoginScreenPhoneLandscape(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TopImageSection(
                imageRes = R.drawable.fotoiniciotablet,
                contentDescription = stringResource(R.string.fotoinicio),
                modifier = Modifier.size(500.dp)
            )

            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.size(width = 500.dp, height = 350.dp)
            ) {
                LoginFormSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    onNavigateToRoute = onNavigateToRoute,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun LoginScreenTabletPortrait(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopImageSection(
            imageRes = R.drawable.fotoinicio,
            contentDescription = stringResource(R.string.fotoinicio),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .background(MaterialTheme.colorScheme.primary)
        )

        LoginFormSection(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            onNavigateToRoute = onNavigateToRoute,
            viewModel = viewModel
        )
    }
}

@Composable
fun LoginScreenPhonePortrait(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopImageSection(
            imageRes = R.drawable.fotoinicio,
            contentDescription = stringResource(R.string.fotoinicio),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f)
                .background(MaterialTheme.colorScheme.primary)
        )

        LoginFormSection(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            onNavigateToRoute = onNavigateToRoute,
            viewModel = viewModel
        )
    }
}


@Composable
fun LoginScreenTabletLandscape(onNavigateToRoute: (String) -> Unit, viewModel: HomeViewModel) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TopImageSection(
                imageRes = R.drawable.fotoiniciotablet,
                contentDescription = stringResource(R.string.fotoinicio),
                modifier = Modifier.size(600.dp)
            )

            Surface(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.size(width = 500.dp, height = 350.dp)
            ) {
                LoginFormSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    onNavigateToRoute = onNavigateToRoute,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun TopImageSection(
    @DrawableRes imageRes: Int,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun LoginFormSection(
    modifier: Modifier = Modifier,
    onNavigateToRoute: (String) -> Unit,
    viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailErrorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.iniciodesesion),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        LoginTextField(
            label = stringResource(R.string.mail),
            keyboardType = KeyboardType.Email,
            value = email,  // Aquí se pasa el valor del email
            onValueChange = { newEmail ->
                email = newEmail
                emailErrorMessage = if (!isValidEmail(newEmail)) {
                    "Correo inválido"
                } else {
                    null
                }
            },
            validate = { it.isNotEmpty() }
        )

        LoginTextField(
            label = stringResource(R.string.contraseña),
            keyboardType = KeyboardType.Password,
            value = password,  // Aquí se pasa el valor de la contraseña
            onValueChange = { newPassword -> password = newPassword },
            isPassword = true,
            validate = { it.isNotEmpty() },
            modifier = Modifier.padding(bottom = 15.dp)
        )

        // Login Button
        LoginButton(
            text = stringResource(R.string.iniciarsesion),
            onClick =
            {
                viewModel.login("arely.nicolas@ethereal.email", "1234567890")
                //viewModel.login(email, password)
                onNavigateToRoute(AppDestinations.INICIO.route)
            },
            backgroundColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.padding(10.dp))

        // Register Button
        LoginButton(
            text = stringResource(R.string.registrarme),
            onClick = { onNavigateToRoute(AppDestinations.REGISTRARME.route) },
            backgroundColor = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.secondary,
            border = true
        )
    }
}


@Composable
fun LoginTextField(
    label: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    validate: (String) -> Boolean,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    value: String,
) {
    var passwordVisible by remember { mutableStateOf(false) }

    var localErrorMessage by remember { mutableStateOf<String?>(null) }

    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            localErrorMessage = if (!validate(input)) {
                "Correo inválido"
            } else {
                null
            }
            onValueChange(input)
        },
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        isError = localErrorMessage != null,
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = stringResource(R.string.toggle_password_visibility)
                    )
                }
            }
        }
    )

    localErrorMessage?.let {
        Text(
            text = it,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}


fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Composable
fun LoginButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color,
    border: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(180.dp)
            .height(50.dp)
            .then(
                if (border) Modifier.border(
                    width = 1.dp,
                    color = textColor,
                    shape = RoundedCornerShape(4.dp)
                ) else Modifier
            ),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        )
    ) {
        Text(text, style = MaterialTheme.typography.titleMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun LoginDialogPreview() {
    PeraAppTheme {
        LoginDialog(
            onDismissRequest = { /* seria volver al inicio */ },
            dialogTitle = stringResource(R.string.estadoiniciarsesion)
        )
    }
}

@Composable
fun LoginDialog(
    onDismissRequest: () -> Unit,
    dialogTitle: String,
    dismissAfterMillis: Long = 3000,
    state: Boolean = false
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
