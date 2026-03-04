package com.alonso.testsnowstore.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alonso.testsnowstore.domain.settings.SettingsInteractor

class SettingsViewModel(
    private val settingsInteractor: SettingsInteractor
) : ViewModel() {

    private val _darkThemeEnabled = MutableLiveData<Boolean>()
    val darkThemeEnabled: LiveData<Boolean> get() = _darkThemeEnabled

    private val _englishLanguageEnabled = MutableLiveData<Boolean>()
    val englishLanguageEnabled: LiveData<Boolean> get() = _englishLanguageEnabled

    init {
        loadThemeState()
        loadLanguageState()
    }

    private fun loadThemeState() {
        _darkThemeEnabled.value = settingsInteractor.isDarkThemeEnabled()
    }

    private fun loadLanguageState() {
        _englishLanguageEnabled.value = settingsInteractor.isEnglishLanguageEnabled()
    }

    fun onThemeToggled(isEnabled: Boolean) {
        settingsInteractor.setDarkThemeEnabled(isEnabled)
        _darkThemeEnabled.value = isEnabled
    }

    fun onLanguageToggled(isEnabled: Boolean) {
        settingsInteractor.setEnglishLanguageEnabled(isEnabled)
        _englishLanguageEnabled.value = isEnabled
    }
}