package com.example.peraapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.peraapp.components.isLandscape
import com.example.peraapp.components.isTablet

object ModularizedFunctions {
    @Composable
    fun ModularizedCardLayout(
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
}
