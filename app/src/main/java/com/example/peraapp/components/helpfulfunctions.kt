package com.example.peraapp.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

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
fun ModularizedLayout(
    contentPhonePortrait: @Composable () -> Unit,
    contentPhoneLandscape: @Composable () -> Unit,
    contentTabletPortrait: @Composable () -> Unit,
    contentTabletLandscape: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    when {
        isTablet(configuration) && isLandscape(configuration) -> {
            contentTabletLandscape()
        }
        isTablet(configuration) -> {
            contentTabletPortrait()
        }
        isLandscape(configuration) -> {
            contentPhoneLandscape()
        }
        else -> {
            contentPhonePortrait()
        }
    }
}
