package com.example.peraapp.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.peraapp.navigation.AppDestinations


val bottomBarItems = listOf(
    AppDestinations.INICIO,
    AppDestinations.MOVIMIENTOS,
    AppDestinations.TARJETAS,
    AppDestinations.CUENTA
)

@Composable
fun BottomBar(
    currentRoute: String?,
    onNavigateToRoute: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        bottomBarItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = stringResource(id = item.text),
                        tint = if (currentRoute == item.route) MaterialTheme.colorScheme.background
                        else MaterialTheme.colorScheme.secondary
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.text),
                        color = if (currentRoute == item.route) MaterialTheme.colorScheme.background
                        else MaterialTheme.colorScheme.secondary
                    )
                },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = { onNavigateToRoute(item.route) }
            )
        }
    }
}