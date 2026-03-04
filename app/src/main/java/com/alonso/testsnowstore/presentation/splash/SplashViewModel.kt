package com.alonso.testsnowstore.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alonso.testsnowstore.domain.ShopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface SplashState {
    data object Loading : SplashState
    data object Ready : SplashState
    data class Error(val message: String) : SplashState
}

class SplashViewModel(
    private val repository: ShopRepository
) : ViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Loading)
    val state: StateFlow<SplashState> = _state

    init {
        viewModelScope.launch {
            runCatching { repository.refreshFirstPage() }
                .onSuccess { _state.value = SplashState.Ready }
                .onFailure { e -> _state.value = SplashState.Error(e.message ?: "Ошибка загрузки") }
        }
    }
}