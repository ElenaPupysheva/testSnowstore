package com.alonso.testsnowstore.domain.settings

class SettingsInteractorImpl(
    private val settingsRepository: SettingsRepository
) : SettingsInteractor {

    override fun isDarkThemeEnabled(): Boolean {
        return settingsRepository.isDarkThemeEnabled()
    }

    override fun setDarkThemeEnabled(enabled: Boolean) {
        settingsRepository.setDarkThemeEnabled(enabled)
    }

    override fun isEnglishLanguageEnabled(): Boolean {
        return settingsRepository.isEnglishLanguageEnabled()
    }

    override fun setEnglishLanguageEnabled(enabled: Boolean) {
        settingsRepository.setEnglishLanguageEnabled(enabled)
    }
}