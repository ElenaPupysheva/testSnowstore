package com.alonso.testsnowstore.data.favourite

import com.alonso.testsnowstore.data.db.dao.FavouriteShopItemDao
import com.alonso.testsnowstore.data.db.entity.FavouriteShopItemEntity
import com.alonso.testsnowstore.domain.favourite.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavouritesRepositoryImpl(
    private val dao: FavouriteShopItemDao
) : FavouritesRepository {

    override fun observeFavouriteIds(): Flow<List<String>> =
        dao.observeAllIds()

    override fun observeFavouriteIdsSet(): Flow<Set<String>> =
        dao.observeAllIds().map { it.toSet() }

    override fun isFavouriteFlow(id: String): Flow<Boolean> =
        dao.isFavoriteFlow(id)

    override suspend fun isFavourite(id: String): Boolean =
        dao.isFavorite(id)

    override suspend fun add(id: String) {
        dao.insert(FavouriteShopItemEntity(id = id))
    }

    override suspend fun remove(id: String) {
        dao.deleteById(id)
    }

    override suspend fun toggle(id: String): Boolean {
        return if (dao.isFavorite(id)) {
            dao.deleteById(id)
            false
        } else {
            dao.insert(FavouriteShopItemEntity(id = id))
            true
        }
    }

    override suspend fun clearAll() {
        dao.clearAll()
    }
}