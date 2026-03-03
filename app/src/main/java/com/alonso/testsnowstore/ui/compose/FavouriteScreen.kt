package com.alonso.testsnowstore.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alonso.testsnowstore.data.ShopItem

@Composable
fun FavouriteScreen(
    shopItems: List<ShopItem>,
    onLoadNextPage: () -> Unit,
    onItemClick: (ShopItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(Modifier.fillMaxSize()) {
        ShopList(
            shopItemsList = shopItems,
            onLoadNextPage = onLoadNextPage,
            onItemClick = onItemClick
        )
    }
}