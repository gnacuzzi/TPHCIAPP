package com.example.peraapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.peraapp.R
import com.example.peraapp.pages.AddCard
import com.example.peraapp.pages.CardsPage
import com.example.peraapp.pages.ChargePage
import com.example.peraapp.pages.DeleteCardPage
import com.example.peraapp.pages.DepositPage
import com.example.peraapp.pages.HomePage
import com.example.peraapp.pages.LoginPage
import com.example.peraapp.pages.MovementsPage
import com.example.peraapp.pages.ProfilePage
import com.example.peraapp.pages.SigninPage
import com.example.peraapp.pages.TransferPage

@Composable
fun AppNavGraph(navController: NavHostController,
                currentRoute: String?,
                onNavigateToRoute: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.INICIO.route
    ){
        composable(route = AppDestinations.INICIO.route){
            HomePage(onNavigateToRoute)
        }
        composable(route = AppDestinations.MOVIMIENTOS.route){
            MovementsPage()
        }
        composable(route = AppDestinations.TARJETAS.route){
            CardsPage(onNavigateToRoute)
        }
        composable(route = AppDestinations.CUENTA.route){
            ProfilePage(//tiene que ser dinamico
                    name = "Samanta",
                    surname = "Jones",
                    mail = "sjones@gmail.com",
                    onNavigateToRoute = onNavigateToRoute
            )
        }
        composable(route = AppDestinations.TRANSFERIR.route){
            TransferPage(onNavigateToRoute)
        }
        composable(route = AppDestinations.INGRESAR.route){
            DepositPage(onNavigateToRoute)
        }
        composable(route = AppDestinations.COBRAR.route) {
            ChargePage(onNavigateToRoute)
        }
        composable(route = AppDestinations.AGREGARTARJETA.route){
            AddCard(onNavigateToRoute)
        }
        composable(route = AppDestinations.ELIMINARTARJETA.route){
            DeleteCardPage()//falta esta porque tiene que ser dinamica segun la tarjeta apretada
        }
        composable(route = AppDestinations.INICIARSESION.route){
            LoginPage(onNavigateToRoute)
        }
        composable(route = AppDestinations.REGISTRARME.route){
            SigninPage(onNavigateToRoute)
        }
    }
}