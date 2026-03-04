package com.alonso.testsnowstore.data

data class ShopItem(
    val id: String,
    val model: String,
    val description: String,
    val features: String,
    val categories: List<String>,
    val imageUrl: String,
    val price: Int,
    val editedAt: Long,
    val isFavorite: Boolean
)
