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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun LoginScreen(onNavigateToRoute: (String) -> Unit) {
    ModularizedLayout(
        contentPhonePortrait = { LoginScreenPhonePortrait(onNavigateToRoute) },
        contentPhoneLandscape = { LoginScreenPhoneLandscape(onNavigateToRoute) },
        contentTabletPortrait = { LoginScreenTabletPortrait(onNavigateToRoute) },
        contentTabletLandscape = { LoginScreenTabletLandscape(onNavigateToRoute) }
    )
}


@Composable
fun LoginScreenPhoneLandscape(onNavigateToRoute: (String) -> Unit) {
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
                    onNavigateToRoute = onNavigateToRoute
                )
            }
        }
    }
}

@Composable
fun LoginScreenTabletPortrait(onNavigateToRoute: (String) -> Unit) {
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
            onNavigateToRoute = onNavigateToRoute
        )
    }
}

@Composable
fun LoginScreenPhonePortrait(onNavigateToRoute: (String) -> Unit) {
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
            onNavigateToRoute = onNavigateToRoute
        )
    }
}


@Composable
fun LoginScreenTabletLandscape(onNavigateToRoute: (String) -> Unit) {
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
                    onNavigateToRoute = onNavigateToRoute
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
    onNavigateToRoute: (String) -> Unit
) {
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
            onClick = { onNavigateToRoute(AppDestinations.INICIO.route) },
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
    value: String,  // Aquí se pasa el valor del campo
) {
    var passwordVisible by remember { mutableStateOf(false) }

    // Declarar errorMessage como var para poder reasignarlo
    var localErrorMessage by remember { mutableStateOf<String?>(null) }

    // Actualiza el errorMessage localmente, no directamente
    OutlinedTextField(
        value = value,
        onValueChange = { input ->
            localErrorMessage = if (!validate(input)) {
                "Correo inválido" // Mensaje de error si no pasa la validación
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


// Function to validate email format using regular expression
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



@PreviewSizes
@Composable
fun LoginScreenPreview(){
    PeraAppTheme {
        LoginScreen{

        }
    }
}