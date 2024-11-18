package com.example.peraapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.peraapp.HomePage
import com.example.peraapp.R
import com.example.peraapp.pages.AddCardContent
import com.example.peraapp.pages.AddCardPage
import com.example.peraapp.pages.CardsPage
import com.example.peraapp.pages.ChargeContent
import com.example.peraapp.pages.ChargePage
import com.example.peraapp.pages.DeleteCardPage
import com.example.peraapp.pages.DepositContent
import com.example.peraapp.pages.DepositPage
import com.example.peraapp.pages.InvestContent
import com.example.peraapp.pages.InvestPage
import com.example.peraapp.pages.LoginPage
import com.example.peraapp.pages.MainScreen
import com.example.peraapp.pages.MovementsPage
import com.example.peraapp.pages.ProfilePage
import com.example.peraapp.pages.SigninPage
import com.example.peraapp.pages.TransferContent
import com.example.peraapp.pages.TransferPage

@Composable
fun AppNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = AppDestinations.INICIO.route
    ){
        //no estoy segura si les tengo que agregar lo del peratheme a estas cosas, veremos
        composable(route = AppDestinations.INICIO.route){
            MainScreen(name = R.string.inicio){ HomePage() }
        }
        composable(route = AppDestinations.MOVIMIENTOS.route){
            MainScreen(name = R.string.movimientos){ MovementsPage() }
        }
        composable(route = AppDestinations.TARJETAS.route){
            MainScreen(name = R.string.tarjetas){ CardsPage() }
        }
        composable(route = AppDestinations.CUENTA.route){
            MainScreen(
                name = R.string.cuenta
            ){
                ProfilePage(//tiene que ser dinamico
                    name = "Samanta",
                    surname = "Jones",
                    mail = "sjones@gmail.com"
                )
            }
        }
        composable(route = AppDestinations.TRANSFERIR.route){
            TransferPage(R.string.transferir){ TransferContent() }
        }
        composable(route = AppDestinations.INGRESAR.route){
            DepositPage { DepositContent() }
        }
        composable(route = AppDestinations.COBRAR.route){
            ChargePage { ChargeContent() }
        }
        composable(route = AppDestinations.INVERTIR.route){
            InvestPage (R.string.invertirrescate) { InvestContent() }
        }
        composable(route = AppDestinations.AGREGARTARJETA.route){
            AddCardPage{ AddCardContent() }
        }
        composable(route = AppDestinations.ELIMINARTARJETA.route){
            MainScreen(name = R.string.tarjeta){ DeleteCardPage() }
        }
        composable(route = AppDestinations.INICIARSESION.route){
            LoginPage()
        }
        composable(route = AppDestinations.REGISTRARME.route){
            SigninPage()
        }
    }
}