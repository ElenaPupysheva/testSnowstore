package com.alonso.testsnowstore.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alonso.testsnowstore.ui.compose.ANIMATION_DELAY
import com.alonso.testsnowstore.ui.compose.DetailedRoute

private const val DETAILS_ROUTE = "details/{id}"

fun NavGraphBuilder.detailsScreenNavigation(
    navController: NavHostController
) {
    composable(
        route = DETAILS_ROUTE,
        arguments = listOf(
            navArgument("id") { type = NavType.StringType }
        ),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(ANIMATION_DELAY)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(ANIMATION_DELAY)
            )
        }
    ) {
        DetailedRoute(
            onBackClick = { navController.popBackStack() }
        )
    }
}