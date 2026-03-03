package com.alonso.testsnowstore.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.alonso.testsnowstore.presentation.favourite.FavouritesViewModel

@Composable
fun FavouriteScreen(
    navController: NavController,
    viewModel: FavouritesViewModel,
    modifier: Modifier = Modifier
) {
    val items by viewModel.favoriteItems.collectAsStateWithLifecycle()

    Column(modifier.fillMaxSize()) {
        ShopList(
            shopItemsList = items,
            onLoadNextPage = { /* обычно не нужно */ },
            onItemClick = { item ->
                // navController.navigate("details/${item.id}")
            }
        )
    }
}