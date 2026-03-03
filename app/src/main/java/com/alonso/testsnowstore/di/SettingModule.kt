package com.alonso.testsnowstore.di

import android.content.Context
import android.content.SharedPreferences
import com.alonso.testsnowstore.data.SHOPITEM_PREFERENCES
import com.alonso.testsnowstore.data.settings.SettingsRepositoryImpl
import com.alonso.testsnowstore.domain.settings.SettingsInteractor
import com.alonso.testsnowstore.domain.settings.SettingsInteractorImpl
import com.alonso.testsnowstore.domain.settings.SettingsRepository
import com.alonso.testsnowstore.presentation.settings.SettingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences(SHOPITEM_PREFERENCES, Context.MODE_PRIVATE)
    }

    single<SettingsRepository> { SettingsRepositoryImpl(get()) }

    single<SettingsInteractor> { SettingsInteractorImpl(get()) }

    viewModel { SettingsViewModel(get()) }

}