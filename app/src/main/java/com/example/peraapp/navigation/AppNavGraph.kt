package com.example.peraapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.peraapp.pages.AddCard
import com.example.peraapp.pages.CardsScreen
import com.example.peraapp.pages.DeleteCardScreen
import com.example.peraapp.pages.DepositScreen
import com.example.peraapp.pages.HomeScreen
import com.example.peraapp.pages.LoginScreen
import com.example.peraapp.pages.MovementsScreen
import com.example.peraapp.pages.ProfileScreen
import com.example.peraapp.pages.SigninScreen
import com.example.peraapp.pages.TransferScreen

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
            HomeScreen(onNavigateToRoute)
        }
        composable(route = AppDestinations.MOVIMIENTOS.route){
            MovementsScreen()
        }
        composable(route = AppDestinations.TARJETAS.route){
            CardsScreen(onNavigateToRoute)
        }
        composable(route = AppDestinations.CUENTA.route){
            ProfileScreen(//tiene que ser dinamico
                    name = "Samanta",
                    surname = "Jones",
                    mail = "sjones@gmail.com",
                    onNavigateToRoute = onNavigateToRoute
            )
        }
        composable(route = AppDestinations.TRANSFERIR.route){
            TransferScreen(onNavigateToRoute)
        }
        composable(route = AppDestinations.INGRESAR.route){
            DepositScreen(onNavigateToRoute)
        }
        composable(route = AppDestinations.AGREGARTARJETA.route){
            AddCard(onNavigateToRoute)
        }
        composable(route = AppDestinations.ELIMINARTARJETA.route){
            DeleteCardScreen()//falta esta porque tiene que ser dinamica segun la tarjeta apretada
        }
        composable(route = AppDestinations.INICIARSESION.route){
            LoginScreen(onNavigateToRoute)
        }
        composable(route = AppDestinations.REGISTRARME.route){
            SigninScreen(onNavigateToRoute)
        }
    }
}