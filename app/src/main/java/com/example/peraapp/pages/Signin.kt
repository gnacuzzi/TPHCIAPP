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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.peraapp.PreviewSizes
import com.example.peraapp.R
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.ui.theme.PeraAppTheme
import com.example.peraapp.components.isLandscape
import com.example.peraapp.components.isTablet
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun SigninPage(onNavigateToRoute: (String) -> Unit) {
    val configuration = LocalConfiguration.current
    val isTablet = isTablet(configuration)
    val isLanscape = isLandscape(configuration)

    if (isTablet) {
        if (isLanscape){
            SigninPageTabletLandscape(onNavigateToRoute)
        }else{
            SigninPageTabletPortrait(onNavigateToRoute)
        }
    } else {
        if (isLanscape){
            SigninPagePhoneLandscape(onNavigateToRoute)
        }else{
            SigninPagePhonePortrait(onNavigateToRoute)
        }
    }
}

@Composable
fun SigninPagePhoneLandscape(onNavigateToRoute: (String) -> Unit) {
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
fun SigninPageTabletPortrait(onNavigateToRoute: (String) -> Unit) {
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
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    var doPasswordsMatch by remember { mutableStateOf(true) }

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
                .height(55.dp)
        )
        OutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            label = { Text("Surname") },
            modifier = Modifier
                .weight(1f)
                .height(55.dp)
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
            .height(55.dp),
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
            .height(55.dp),
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
            .height(55.dp),
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
fun SigninPagePhonePortrait(onNavigateToRoute: (String) -> Unit) {
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
fun SigninPageTabletLandscape(onNavigateToRoute: (String) -> Unit) {
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
fun SigninPagePreview(){
    PeraAppTheme {
        SigninPage{

        }
    }
}