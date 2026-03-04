package com.alonso.testsnowstore.data

import com.alonso.testsnowstore.data.api.ApiService
import com.alonso.testsnowstore.data.db.dao.FavouriteShopItemDao
import com.alonso.testsnowstore.data.db.entity.FavouriteShopItemEntity
import com.alonso.testsnowstore.data.mappers.toDomain
import com.alonso.testsnowstore.domain.ShopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ShopRepositoryImpl(
    private val api: ApiService,
    private val pageSize: Int = 20,
    private val favouriteDao: FavouriteShopItemDao,
) : ShopRepository {
    private val mutex = Mutex()
    private val _cache = MutableStateFlow<List<ShopItem>>(emptyList())
    private var offset = 0
    private var endReached = false

    override fun observeItems(): Flow<List<ShopItem>> =
        combine(_cache.asStateFlow(), favouriteDao.observeAllIds()) { items, favIds ->
            val favSet = favIds.toHashSet()
            items.map { it.copy(isFavorite = favSet.contains(it.id)) }
        }.distinctUntilChanged()

    override fun observeItem(id: String): Flow<ShopItem?> =
        combine(_cache.asStateFlow(), favouriteDao.isFavoriteFlow(id)) { items, isFav ->
            items.firstOrNull { it.id == id }?.copy(isFavorite = isFav)
        }.distinctUntilChanged()

    override suspend fun toggleFavorite(id: String) {
        if (favouriteDao.isFavorite(id)) {
            favouriteDao.deleteById(id)
        } else {
            favouriteDao.insert(
                FavouriteShopItemEntity(
                    id = id,
                    addedAt = System.currentTimeMillis()
                )
            )
        }
    }

    override suspend fun refreshFirstPage() {
        mutex.withLock {
            offset = 0
            endReached = false

            val page = api.getItems(limit = pageSize, offset = offset)
            _cache.value = page.map { it.toDomain(isFavorite = false) }

            offset += page.size
            if (page.size < pageSize) endReached = true
        }
    }

    override suspend fun loadNextPage() {
        mutex.withLock {
            if (endReached) return

            val page = api.getItems(limit = pageSize, offset = offset)
            if (page.isEmpty()) {
                endReached = true
                return
            }

            val existingIds = _cache.value.asSequence().map { it.id }.toHashSet()
            val mapped = page
                .filter { it.id !in existingIds }
                .map { it.toDomain(isFavorite = false) }

            _cache.value = _cache.value + mapped

            offset += page.size
            if (page.size < pageSize) endReached = true
        }
    }
}