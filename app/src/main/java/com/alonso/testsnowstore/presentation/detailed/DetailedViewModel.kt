package com.alonso.testsnowstore.presentation.detailed

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alonso.testsnowstore.data.DETAILS_ARG_ID
import com.alonso.testsnowstore.domain.ShopRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailedViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: ShopRepository
) : ViewModel() {

    private val id: String = requireNotNull(savedStateHandle[DETAILS_ARG_ID])

    val uiState: StateFlow<DetailedUiState> =
        repository.observeItem(id)
            .map { item ->
                if (item == null) DetailedUiState.Loading else DetailedUiState.Content(
                    item
                )
            }
            .catch { e -> emit(DetailedUiState.Error(e.message ?: "Ошибка")) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), DetailedUiState.Loading)

    fun onToggleFavorite() {
        viewModelScope.launch { repository.toggleFavorite(id) }
    }
}