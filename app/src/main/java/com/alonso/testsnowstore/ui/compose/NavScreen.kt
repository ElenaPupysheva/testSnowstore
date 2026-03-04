package com.alonso.testsnowstore.ui.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alonso.testsnowstore.R
import com.alonso.testsnowstore.domain.BottomNavRoutes
import com.alonso.testsnowstore.presentation.favourite.FavouritesViewModel
import com.alonso.testsnowstore.presentation.main.MainShopViewModel
import com.alonso.testsnowstore.presentation.settings.SettingsViewModel
import com.alonso.testsnowstore.ui.navigation.detailsScreenNavigation
import com.alonso.testsnowstore.ui.navigation.favouriteScreenNavigation
import com.alonso.testsnowstore.ui.navigation.mainScreenNavigation
import com.alonso.testsnowstore.ui.navigation.settingsScreenNavigation
import org.koin.androidx.compose.koinViewModel

const val ANIMATION_DELAY = 500
const val ZERO_DELAY = 0

@Composable
fun NavScreen() {
    val bottomBarItems = listOf(
        BottomBarItem(labelRes = R.string.main_list, route = BottomNavRoutes.Main),
        BottomBarItem(labelRes = R.string.screen_favourites, route = BottomNavRoutes.Favourites),
        BottomBarItem(labelRes = R.string.screen_setting, route = BottomNavRoutes.Settings),
    )

    val mainShopViewModel: MainShopViewModel = koinViewModel()
    val settingsViewModel: SettingsViewModel = koinViewModel()
    val favouritesViewModel: FavouritesViewModel = koinViewModel()

    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val tabRouteNames = remember { BottomNavRoutes.entries.map { it.name }.toSet() }
    val showBottomBar = currentRoute in tabRouteNames || currentRoute.isNullOrEmpty()

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = ANIMATION_DELAY)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = ZERO_DELAY)
                )
            ) {
                Column {
                    HorizontalDivider()
                    NavigationBar {
                        val currentDestination = backStackEntry?.destination
                        bottomBarItems.forEach { item ->
                            val selected = currentDestination?.route == item.route.name

                            NavigationBarItem(
                                icon = { /* icons позже */ },
                                label = { Text(stringResource(item.labelRes)) },
                                selected = selected,
                                onClick = {
                                    if (selected) return@NavigationBarItem

                                    navController.navigate(item.route.name) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavRoutes.Main.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            mainScreenNavigation(
                navController = navController,
                viewModel = mainShopViewModel
            )
            favouriteScreenNavigation(
                navController = navController,
                viewModel = favouritesViewModel
            )
            settingsScreenNavigation(
                viewModel = settingsViewModel
            )

            detailsScreenNavigation(navController)
        }
    }
}

data class BottomBarItem(
    val labelRes: Int,
    val route: BottomNavRoutes
)