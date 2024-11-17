package com.example.peraapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.peraapp.R

data class SideBarItem(
    val iconResId: Int,
    val text: String,
    val onClick: () -> Unit
)

val sideBarItems = listOf(
    SideBarItem(
        iconResId = R.drawable.home,
        text = "Inicio",
        onClick = { /* Acción para "Inicio" */ }
    ),
    SideBarItem(
        iconResId = R.drawable.movimientos,
        text = "Movimientos",
        onClick = { /* Acción para "Movimientos" */ }
    ),
    SideBarItem(
        iconResId = R.drawable.tarjetas,
        text = "Tarjetas",
        onClick = { /* Acción para "Tarjetas" */ }
    ),
    SideBarItem(
        iconResId = R.drawable.cuenta,
        text = "Cuenta",
        onClick = { /* Acción para "Cuenta" */ }
    )
)

@Composable
fun SideBar() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(100.dp)
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo pera",
            modifier = Modifier.size(70.dp)
        )
        sideBarItems.forEach { item ->
            SideBarItem(item = item)
        }
    }
}

@Composable
fun SideBarItem(item: SideBarItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = item.onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val icon: Painter = painterResource(id = item.iconResId)
        Image(
            painter = icon,
            contentDescription = item.text,
            modifier = Modifier.size(50.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
        )
    }
}