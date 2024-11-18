package com.example.peraapp.navigation

import androidx.annotation.StringRes
import com.example.peraapp.R

enum class AppDestinations(
    @StringRes val title: Int,
    val route: String
){
    INICIO(R.string.inicio, "inicio"),
    MOVIMIENTOS(R.string.movimientos, "movimientos"),
    TARJETAS(R.string.tarjetas, "tarjetas"),
    CUENTA(R.string.cuenta, "cuenta"),
    TRANSFERIR(R.string.transferir, "transferir"),
    INGRESAR(R.string.ingresar, "ingresar"),
    COBRAR(R.string.cobrar, "cobrar"),
    INVERTIR(R.string.invertir, "invertirrescate"),
    AGREGARTARJETA(R.string.agregartarjeta, "agregartarjeta"),
    ELIMINARTARJETA(R.string.eliminartarjeta, "eliminartarjeta"), //esta deberia ser dinamica quizas
    INICIARSESION(R.string.iniciarsesion, "iniciarsesion"),
    REGISTRARME(R.string.registrarme, "registrarme")
}