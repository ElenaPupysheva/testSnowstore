package com.alonso.testsnowstore.domain.settings

interface SettingsInteractor {
    fun isDarkThemeEnabled(): Boolean
    fun setDarkThemeEnabled(enabled: Boolean)
    fun isEnglishLanguageEnabled(): Boolean
    fun setEnglishLanguageEnabled(enabled: Boolean)
}