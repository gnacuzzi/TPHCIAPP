package com.example.peraapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.peraapp.R

enum class AppDestinations(
    @DrawableRes val iconResId: Int,
    @StringRes val text: Int,
    val route: String
){
    INICIO(R.drawable.home, R.string.inicio, "inicio"),
    MOVIMIENTOS(R.drawable.movimientos, R.string.movimientos, "movimientos"),
    TARJETAS(R.drawable.tarjetas, R.string.tarjetas, "tarjetas"),
    CUENTA(R.drawable.cuenta, R.string.cuenta, "cuenta"),
    TRANSFERIR(R.drawable.transferir, R.string.transferir, "transferir"),
    INGRESAR(R.drawable.ingresar, R.string.ingresar, "ingresar"),
    COBRAR(R.drawable.cobrar, R.string.cobrar, "cobrar"),
    INVERTIR(R.drawable.invest, R.string.invertir, "invertirrescate"),
    AGREGARTARJETA(R.drawable.tarjetas, R.string.agregartarjeta, "agregartarjeta"),
    ELIMINARTARJETA(R.drawable.tarjetas, R.string.eliminartarjeta, "eliminartarjeta"), //esta deberia ser dinamica quizas
    INICIARSESION(R.drawable.cuenta, R.string.iniciarsesion, "iniciarsesion"),
    REGISTRARME(R.drawable.cuenta, R.string.registrarme, "registrarme")
}