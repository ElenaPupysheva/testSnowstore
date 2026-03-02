package com.alonso.testsnowstore.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alonso.testsnowstore.data.db.entity.FavoriteShopItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteShopItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: FavoriteShopItemEntity): Long

    @Query("DELETE FROM favorite_shopItems WHERE id = :id")
    suspend fun deleteById(id: Int): Int

    @Query("DELETE FROM favorite_shopItems")
    suspend fun clearAll(): Int

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_shopItems WHERE id = :id)")
    fun isFavoriteFlow(id: Int): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_shopItems WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean

    @Query("SELECT id FROM favorite_shopItems ORDER BY addedAt DESC")
    fun observeAllIds(): Flow<List<String>>
}