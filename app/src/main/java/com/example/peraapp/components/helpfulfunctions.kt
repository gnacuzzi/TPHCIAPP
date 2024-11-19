package com.example.peraapp.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.peraapp.R

@Composable
fun isTablet(configuration: Configuration): Boolean {
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    return screenWidth >= 600 && screenHeight >= 600
}

@Composable
fun isLandscape(configuration: Configuration): Boolean {
    return configuration.screenWidthDp > configuration.screenHeightDp
}

@Composable
fun BackButton(onNavigateToRoute: (String) -> Unit) {
    IconButton(
        onClick = { onNavigateToRoute("BACK") },
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = stringResource(R.string.volveratras),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}