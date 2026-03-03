package com.alonso.testsnowstore.data.settings

import android.content.SharedPreferences
import com.alonso.testsnowstore.data.ENGLISH_LANGUAGE
import com.alonso.testsnowstore.data.SWITCH_KEY
import com.alonso.testsnowstore.domain.settings.SettingsRepository

class SettingsRepositoryImpl(private val sharedPreferences: SharedPreferences) :
    SettingsRepository {
    override fun isDarkThemeEnabled(): Boolean {
        return sharedPreferences.getBoolean(SWITCH_KEY, false)
    }

    override fun setDarkThemeEnabled(enabled: Boolean) {
        sharedPreferences.edit()
            .putBoolean(SWITCH_KEY, enabled)
            .apply()
    }

    override fun isEnglishLanguageEnabled(): Boolean {
        return sharedPreferences.getBoolean(ENGLISH_LANGUAGE, false)
    }

    override fun setEnglishLanguageEnabled(enabled: Boolean) {
        sharedPreferences.edit()
            .putBoolean(ENGLISH_LANGUAGE, enabled)
            .apply()
    }
}