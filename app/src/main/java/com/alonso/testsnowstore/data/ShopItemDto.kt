package com.alonso.testsnowstore.data

import com.squareup.moshi.Json

data class ShopItemDto(
    val id: String,
    val model: String,
    val description: String,
    val features: String,
    val categories: List<String>,
    @Json(name = "imageUrl") val imageUrl: String,
    val price: Int,
    val editedAt: Long
)
