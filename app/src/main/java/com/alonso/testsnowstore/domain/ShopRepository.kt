package com.alonso.testsnowstore.domain

import com.alonso.testsnowstore.data.ShopItem
import kotlinx.coroutines.flow.Flow

interface ShopRepository {
    fun observeItems(): Flow<List<ShopItem>>
    suspend fun refreshFirstPage()
    suspend fun loadNextPage()
    fun observeItem(id: String): Flow<ShopItem?>
    suspend fun toggleFavorite(id: String)
}