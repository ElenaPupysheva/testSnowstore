package com.alonso.testsnowstore.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.alonso.testsnowstore.data.ShopItem
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun ShopList(
    shopItemsList: List<ShopItem>,
    onLoadNextPage: () -> Unit,
    onItemClick: (shopItem: ShopItem) -> Unit
) {
    val listState = rememberLazyListState()
    val shouldLoadNext = remember {
        derivedStateOf {
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
            val totalItemsCount = listState.layoutInfo.totalItemsCount

            lastVisibleItemIndex != null && lastVisibleItemIndex >= totalItemsCount - 1
        }
    }
    val preloadThreshold = 3

    LaunchedEffect(listState) {
        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val total = layoutInfo.totalItemsCount
            val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            total > 0 && lastVisible >= total - 1 - preloadThreshold
        }
            .distinctUntilChanged()
            .filter { it }
            .collect {
                onLoadNextPage()
            }
    }

    LazyColumn(
        Modifier.fillMaxSize(),
        state = listState
    ) {
        items(
            items = shopItemsList
        ) { shopItem ->
            ItemCard(
                shopItem = shopItem,
                onClick = { onItemClick(shopItem) }
            )
        }
    }
}