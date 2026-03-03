package com.alonso.testsnowstore

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.alonso.testsnowstore.data.ENGLISH_LANGUAGE
import com.alonso.testsnowstore.data.SHOPITEM_PREFERENCES
import com.alonso.testsnowstore.data.SWITCH_KEY
import com.alonso.testsnowstore.di.databaseModule
import com.alonso.testsnowstore.di.favouriteModule
import com.alonso.testsnowstore.di.mainModule
import com.alonso.testsnowstore.di.networkModule
import com.alonso.testsnowstore.di.settingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    private lateinit var prefs: SharedPreferences
    var darkTheme = false

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
                    favouriteModule
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
                (resources.configuration.uiMode and
                        android.content.res.Configuration.UI_MODE_NIGHT_MASK) ==
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

        val locales = if (isEnglish) {
            LocaleListCompat.forLanguageTags("en")
        } else {
            LocaleListCompat.forLanguageTags("ru")
        }

        AppCompatDelegate.setApplicationLocales(locales)
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
        prefs.edit().putBoolean(ENGLISH_LANGUAGE, isEnglish).apply()

        val locales = if (isEnglish) {
            LocaleListCompat.forLanguageTags("en")
        } else {
            LocaleListCompat.forLanguageTags("ru")
        }

        AppCompatDelegate.setApplicationLocales(locales)
    }
}