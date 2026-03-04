package com.alonso.testsnowstore.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alonso.testsnowstore.data.db.entity.FavouriteShopItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteShopItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: FavouriteShopItemEntity): Long

    @Query("DELETE FROM favourite_shopItems WHERE id = :id")
    suspend fun deleteById(id: String): Int

    @Query("DELETE FROM favourite_shopItems")
    suspend fun clearAll(): Int

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_shopItems WHERE id = :id)")
    fun isFavoriteFlow(id: String): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_shopItems WHERE id = :id)")
    suspend fun isFavorite(id: String): Boolean

    @Query("SELECT id FROM favourite_shopItems ORDER BY addedAt DESC")
    fun observeAllIds(): Flow<List<String>>
}