package com.alonso.testsnowstore.di

import com.alonso.testsnowstore.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel { SplashViewModel(repository = get()) }
}