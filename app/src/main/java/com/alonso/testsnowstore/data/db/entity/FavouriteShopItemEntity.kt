package com.alonso.testsnowstore.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_shopItems")
data class FavouriteShopItemEntity(
    @PrimaryKey val id: String,
    val addedAt: Long = System.currentTimeMillis()
)
