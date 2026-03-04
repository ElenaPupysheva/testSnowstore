package com.alonso.testsnowstore.di

import com.alonso.testsnowstore.data.favourite.FavouritesRepositoryImpl
import com.alonso.testsnowstore.domain.favourite.FavouritesRepository
import com.alonso.testsnowstore.presentation.favourite.FavouritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {
    single<FavouritesRepository> {
        FavouritesRepositoryImpl(get())
    }

    viewModel {
        FavouritesViewModel(
            shopRepository = get(),
            favourites = get()
        )
    }
}