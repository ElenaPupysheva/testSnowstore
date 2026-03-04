package com.alonso.testsnowstore.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

private const val ROUTE_SPLASH = "splash"
private const val ROUTE_MAIN = "main"

@Composable
fun RootHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_SPLASH,
        modifier = modifier
    ) {
        composable(route = ROUTE_SPLASH) {
            SplashRoute(
                onFinished = {
                    navController.navigate(ROUTE_MAIN) {
                        popUpTo(ROUTE_SPLASH) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(route = ROUTE_MAIN) {
            NavScreen()
        }
    }
}