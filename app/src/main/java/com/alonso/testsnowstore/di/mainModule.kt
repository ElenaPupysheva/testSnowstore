package com.alonso.testsnowstore.di

import com.alonso.testsnowstore.data.ShopRepositoryImpl
import com.alonso.testsnowstore.domain.ShopRepository
import com.alonso.testsnowstore.presentation.main.MainShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<ShopRepository> {
        ShopRepositoryImpl(
            api = get(),
            pageSize = 20,
            favouriteDao = get()
        )
    }
    viewModel { MainShopViewModel(repository = get()) }
}