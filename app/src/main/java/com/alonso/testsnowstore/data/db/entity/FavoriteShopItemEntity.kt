package com.alonso.testsnowstore.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_shopItems")
data class FavoriteShopItemEntity(
    @PrimaryKey val id: String,
    val addedAt: Long = System.currentTimeMillis()
)
