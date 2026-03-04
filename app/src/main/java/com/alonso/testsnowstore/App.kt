package com.alonso.testsnowstore

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.alonso.testsnowstore.data.ENGLISH_LANGUAGE
import com.alonso.testsnowstore.data.SHOPITEM_PREFERENCES
import com.alonso.testsnowstore.data.SWITCH_KEY
import com.alonso.testsnowstore.di.databaseModule
import com.alonso.testsnowstore.di.detailedModule
import com.alonso.testsnowstore.di.favouriteModule
import com.alonso.testsnowstore.di.mainModule
import com.alonso.testsnowstore.di.networkModule
import com.alonso.testsnowstore.di.settingsModule
import com.alonso.testsnowstore.di.splashModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    private lateinit var prefs: SharedPreferences

    var darkTheme: Boolean = false
        private set

    private val _langTag = MutableStateFlow("ru")
    val langTag: StateFlow<String> = _langTag.asStateFlow()

    override fun onCreate() {
        super.onCreate()

        prefs = getSharedPreferences(SHOPITEM_PREFERENCES, MODE_PRIVATE)

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    mainModule,
                    databaseModule,
                    settingsModule,
                    favouriteModule,
                    detailedModule,
                    splashModule
                )
            )
        }

        initThemeIfMissing()
        applySavedTheme()
        applySavedLanguage()
    }

    private fun initThemeIfMissing() {
        if (!prefs.contains(SWITCH_KEY)) {
            val isSystemDarkTheme =
                (resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK) ==
                        android.content.res.Configuration.UI_MODE_NIGHT_YES

            prefs.edit().putBoolean(SWITCH_KEY, isSystemDarkTheme).apply()
        }
    }

    private fun applySavedTheme() {
        darkTheme = prefs.getBoolean(SWITCH_KEY, false)
        AppCompatDelegate.setDefaultNightMode(
            if (darkTheme) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    private fun applySavedLanguage() {
        val isEnglish = prefs.getBoolean(ENGLISH_LANGUAGE, false)
        _langTag.value = if (isEnglish) "en" else "ru"
    }

    fun switchTheme(darkThemeEnabled: Boolean) {
        darkTheme = darkThemeEnabled
        prefs.edit().putBoolean(SWITCH_KEY, darkThemeEnabled).apply()

        AppCompatDelegate.setDefaultNightMode(
            if (darkThemeEnabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    fun switchLanguage(isEnglish: Boolean) {
        val tag = if (isEnglish) "en" else "ru"
        prefs.edit().putBoolean(ENGLISH_LANGUAGE, isEnglish).apply()
        _langTag.value = tag
    }
}