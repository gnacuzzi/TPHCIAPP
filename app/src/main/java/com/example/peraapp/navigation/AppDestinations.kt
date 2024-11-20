package com.example.peraapp.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.MoneyOff
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Update
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.peraapp.R

enum class AppDestinations(
    val icon: ImageVector,
    @StringRes val text: Int,
    val route: String
){
    INICIO(Icons.Outlined.Home, R.string.inicio, "inicio"),
    MOVIMIENTOS(Icons.Outlined.Update, R.string.movimientos, "movimientos"),
    TARJETAS(Icons.Outlined.CreditCard, R.string.tarjetas, "tarjetas"),
    CUENTA(Icons.Outlined.Person, R.string.cuenta, "cuenta"),
    TRANSFERIR(Icons.Outlined.MoneyOff, R.string.transferir, "transferir"),
    INGRESAR(Icons.Outlined.AttachMoney, R.string.ingresar, "ingresar"),
    AGREGARTARJETA(Icons.Outlined.CreditCard, R.string.agregartarjeta, "agregartarjeta"),
    ELIMINARTARJETA(Icons.Outlined.CreditCard, R.string.eliminartarjeta, "eliminartarjeta"), //esta deberia ser dinamica quizas
    INICIARSESION(Icons.Outlined.Person, R.string.iniciarsesion, "iniciarsesion"),
    REGISTRARME(Icons.Outlined.Person, R.string.registrarme, "registrarme"),
    CERRARSESION(Icons.AutoMirrored.Outlined.Logout, R.string.cerrarsesion, "iniciarsesion")
}