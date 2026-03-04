package com.alonso.testsnowstore.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.alonso.testsnowstore.domain.BottomNavRoutes
import com.alonso.testsnowstore.presentation.main.MainShopViewModel
import com.alonso.testsnowstore.ui.compose.MainShopListScreen

fun NavGraphBuilder.mainScreenNavigation(
    navController: NavHostController,
    viewModel: MainShopViewModel
) {
    composable(route = BottomNavRoutes.Main.name) {
        MainShopListScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}