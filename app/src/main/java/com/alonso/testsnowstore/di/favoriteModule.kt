package com.alonso.testsnowstore.di

import com.alonso.testsnowstore.data.favourite.FavouritesRepositoryImpl
import com.alonso.testsnowstore.domain.favourite.FavouritesRepository

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {

    single<FavouritesRepository> {
        FavouritesRepositoryImpl(get())
    }

    viewModel {
        FavouritesViewModel(favorites = get())
    }
}