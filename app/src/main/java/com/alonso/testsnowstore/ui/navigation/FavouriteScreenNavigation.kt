package com.alonso.testsnowstore.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.alonso.testsnowstore.domain.BottomNavRoutes
import com.alonso.testsnowstore.presentation.favourite.FavouritesViewModel
import com.alonso.testsnowstore.ui.compose.FavouriteScreen

private const val ANIMATION_DELAY = 500

fun NavGraphBuilder.favouriteScreenNavigation(
    navController: NavHostController,
    viewModel: FavouritesViewModel
) {
    composable(
        route = BottomNavRoutes.Favourites.name,
        enterTransition = {
            val fromRoute = initialState.destination.route
            if (fromRoute != null && BottomNavRoutes.isInEnum(fromRoute)) {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(ANIMATION_DELAY)
                )
            } else {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    tween(ANIMATION_DELAY)
                )
            }
        },
        exitTransition = {
            val toRoute = targetState.destination.route
            if (toRoute != null && BottomNavRoutes.isInEnum(toRoute)) {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(ANIMATION_DELAY)
                )
            } else {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Down,
                    tween(ANIMATION_DELAY)
                )
            }
        }
    ) {
        FavouriteScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}