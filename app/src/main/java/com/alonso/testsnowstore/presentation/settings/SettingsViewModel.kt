package com.alonso.testsnowstore.presentation.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel(
    private val settingsInteractor: SettingsInteractor
) : ViewModel() {
    private val _darkThemeEnabled = MutableLiveData<Boolean>()
    val darkThemeEnabled: LiveData<Boolean> get() = _darkThemeEnabled

    init {
        loadThemeState()
    }

    private fun loadThemeState() {
        val isEnabled = settingsInteractor.isDarkThemeEnabled()
        _darkThemeEnabled.postValue(isEnabled)
    }

    fun onThemeToggled(isEnabled: Boolean) {
        settingsInteractor.setDarkThemeEnabled(isEnabled)
        _darkThemeEnabled.value = isEnabled
    }
}