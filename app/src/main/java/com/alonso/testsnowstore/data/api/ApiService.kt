package com.alonso.testsnowstore.data.api

import com.alonso.testsnowstore.data.ShopItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("items")
    suspend fun getItems(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): List<ShopItemDto>

}