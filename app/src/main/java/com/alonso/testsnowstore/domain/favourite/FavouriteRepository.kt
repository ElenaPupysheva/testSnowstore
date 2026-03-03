package com.alonso.testsnowstore.domain.favourite

import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    fun observeFavouriteIds(): Flow<List<String>>
    fun observeFavouriteIdsSet(): Flow<Set<String>>
    fun isFavouriteFlow(id: String): Flow<Boolean>
    suspend fun isFavourite(id: String): Boolean
    suspend fun add(id: String)
    suspend fun remove(id: String)
    suspend fun toggle(id: String): Boolean
    suspend fun clearAll()
}