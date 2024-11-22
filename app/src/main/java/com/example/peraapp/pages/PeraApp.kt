package com.example.peraapp.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.peraapp.HomeViewModel
import com.example.peraapp.PeraApplication
import com.example.peraapp.PreviewSizes
import com.example.peraapp.components.BottomBar
import com.example.peraapp.components.SideBar
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.navigation.AppNavGraph
import com.example.peraapp.ui.theme.PeraAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as PeraApplication))
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.getPayments()
    val navController = rememberNavController()
    val startDestination = if (uiState.isAuthenticated) AppDestinations.INICIO.route else AppDestinations.INICIARSESION.route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600
    val routesWithoutBars = listOf(
        AppDestinations.INICIARSESION.route,
        AppDestinations.REGISTRARME.route,
        AppDestinations.VERIFICACION.route
    )
    val showBars = currentRoute !in routesWithoutBars

    Surface(color = MaterialTheme.colorScheme.background) {
        if (isTablet) {
            if (showBars) {
                Row(modifier = Modifier.fillMaxSize()) {
                    SideBar(currentRoute = currentRoute) { route ->
                        navigateSafely(navController, route, uiState.isAuthenticated)
                    }
                    AppNavGraph(
                        navController = navController,
                        startDestination = startDestination,
                        currentRoute = currentRoute,
                        viewModel = viewModel,
                    ) { route ->
                        navigateSafely(navController, route, uiState.isAuthenticated)
                    }
                }
            } else {
                AppNavGraph(
                    navController = navController,
                    startDestination = startDestination,
                    currentRoute = currentRoute,
                    viewModel = viewModel,
                ) { route ->
                    navigateSafely(navController, route, uiState.isAuthenticated)
                }
            }
        } else {
            if (showBars) {
                Scaffold(
                    bottomBar = {
                        BottomBar(currentRoute = currentRoute) { route ->
                            navigateSafely(navController, route, uiState.isAuthenticated)
                        }
                    }
                ) {
                    AppNavGraph(
                        navController = navController,
                        startDestination = startDestination,
                        currentRoute = currentRoute,
                        viewModel = viewModel,
                    ) { route ->
                        navigateSafely(navController, route, uiState.isAuthenticated)
                    }
                }
            } else {
                AppNavGraph(
                    navController = navController,
                    startDestination = startDestination,
                    currentRoute = currentRoute,
                    viewModel = viewModel,
                ) { route ->
                    navigateSafely(navController, route, uiState.isAuthenticated)
                }
            }
        }
    }
}

private fun navigateSafely(
    navController: NavHostController,
    route: String,
    isAuthenticated: Boolean
) {
    val routesWithoutAuthentication = listOf(
        AppDestinations.INICIARSESION.route,
        AppDestinations.REGISTRARME.route,
        AppDestinations.VERIFICACION.route
    )

    if (isAuthenticated || route in routesWithoutAuthentication) {
        if (navController.currentDestination?.route != route) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                if (route == AppDestinations.INICIARSESION.route || route == AppDestinations.INICIO.route) {
                    popUpTo(AppDestinations.INICIARSESION.route) { inclusive = true }
                }
            }
        }
    } else {
        navController.navigate(AppDestinations.INICIARSESION.route) {
            popUpTo(AppDestinations.INICIARSESION.route) { inclusive = true }
        }
    }
}



@PreviewSizes
@Composable
fun PeraAppPreview(){
    PeraAppTheme {
        MainScreen()
    }
}