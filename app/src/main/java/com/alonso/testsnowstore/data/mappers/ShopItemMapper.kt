package com.alonso.testsnowstore.data.mappers

import com.alonso.testsnowstore.data.ShopItem
import com.alonso.testsnowstore.data.ShopItemDto

fun ShopItemDto.toDomain(): ShopItem = ShopItem(
    id = id,
    model = model,
    description = description,
    features = features,
    categories = categories,
    imageUrl = imageUrl,
    price = price,
    editedAt = editedAt
)