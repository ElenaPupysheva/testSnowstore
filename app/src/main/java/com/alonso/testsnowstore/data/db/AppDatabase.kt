package com.alonso.testsnowstore.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alonso.testsnowstore.data.db.dao.FavoriteShopItemDao
import com.alonso.testsnowstore.data.db.entity.FavoriteShopItemEntity

@Database(
    entities = [FavoriteShopItemEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteShopItemDao(): FavoriteShopItemDao

}