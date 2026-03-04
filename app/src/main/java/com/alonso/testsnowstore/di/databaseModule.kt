package com.alonso.testsnowstore.di

import androidx.room.Room
import com.alonso.testsnowstore.data.db.AppDatabase
import com.alonso.testsnowstore.data.db.dao.FavouriteShopItemDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "shop_database"
        ).build()
    }

    single<FavouriteShopItemDao> {
        get<AppDatabase>().favouriteShopItemDao()
    }
}