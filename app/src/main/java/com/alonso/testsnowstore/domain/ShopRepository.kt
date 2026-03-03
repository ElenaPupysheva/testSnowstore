package com.alonso.testsnowstore.domain

import com.alonso.testsnowstore.data.ShopItem
import kotlinx.coroutines.flow.Flow

interface ShopRepository {
    fun observeItems(): Flow<List<ShopItem>>
    suspend fun refreshFirstPage()
    suspend fun loadNextPage()
}