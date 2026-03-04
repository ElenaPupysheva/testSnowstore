package com.alonso.testsnowstore.di

import com.alonso.testsnowstore.presentation.detailed.DetailedViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val detailedModule = module {
    viewModel { DetailedViewModel(get(), get()) }
}