package com.example.peraapp.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.peraapp.PreviewSizes
import com.example.peraapp.components.BottomBar
import com.example.peraapp.components.SideBar
import com.example.peraapp.navigation.AppDestinations
import com.example.peraapp.navigation.AppNavGraph
import com.example.peraapp.ui.theme.PeraAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val configuration = LocalConfiguration.current
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isTablet = configuration.screenWidthDp >= 600
    val routesWithoutBars = listOf(
        AppDestinations.INICIARSESION.route,
        AppDestinations.REGISTRARME.route
    )
    val showBars = currentRoute !in routesWithoutBars

    if (isTablet) {
        Surface(color = MaterialTheme.colorScheme.background) {
            if (showBars) {
                Row(modifier = Modifier.fillMaxSize()) {
                    SideBar(currentRoute = currentRoute) { route ->
                        if (route == "BACK") {
                            navController.popBackStack()
                        } else {
                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                    AppNavGraph(
                        navController = navController,
                        currentRoute = currentRoute
                    ) { route ->
                        if (route == "BACK") {
                            navController.popBackStack()
                        } else {
                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                }
            } else {
                AppNavGraph(
                    navController = navController,
                    currentRoute = currentRoute
                ) { route ->
                    if (route == "BACK") {
                        navController.popBackStack()
                    } else {
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        }
    } else {
        if (showBars) {
            Scaffold(
                bottomBar = {
                    BottomBar(currentRoute = currentRoute) { route ->
                        if (route == "BACK") {
                            navController.popBackStack()
                        } else {
                            navController.navigate(route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                }
            ) {
                AppNavGraph(
                    navController = navController,
                    currentRoute = currentRoute
                ) { route ->
                    if (route == "BACK") {
                        navController.popBackStack()
                    } else {
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        } else {
            AppNavGraph(
                navController = navController,
                currentRoute = currentRoute
            ) { route ->
                if (route == "BACK") {
                    navController.popBackStack()
                } else {
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
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