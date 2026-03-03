package com.alonso.testsnowstore.presentation.main

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alonso.testsnowstore.data.ShopItem
import com.alonso.testsnowstore.domain.ShopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class MainShopUiState(
    val items: List<ShopItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class MainShopViewModel(
    private val repository: ShopRepository
) : ViewModel() {

    val textFieldState = TextFieldState()

    private val query = MutableStateFlow("")
    private val loading = MutableStateFlow(false)
    private val error = MutableStateFlow<String?>(null)

    private val itemsFlow = repository.observeItems()

    private val filteredItems =
        combine(itemsFlow, query) { items, q ->
            val s = q.trim()
            if (s.isEmpty()) items
            else items.filter { it.model.contains(s, ignoreCase = true) }
        }

    val uiState: StateFlow<MainShopUiState> =
        combine(filteredItems, loading, error) { items, isLoading, err ->
            MainShopUiState(items = items, isLoading = isLoading, error = err)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            MainShopUiState(isLoading = true)
        )

    val searchResults: StateFlow<List<String>> =
        combine(itemsFlow, query) { items, q ->
            val s = q.trim()
            if (s.isEmpty()) emptyList()
            else items.asSequence()
                .map { it.model }
                .filter { it.contains(s, ignoreCase = true) }
                .distinct()
                .take(10)
                .toList()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    init {
        refresh()
    }

    fun onSearch(q: String) {
        query.value = q
    }

    fun refresh() {
        viewModelScope.launch {
            loading.value = true
            error.value = null
            runCatching { repository.refreshFirstPage() }
                .onFailure { error.value = it.message ?: "Ошибка загрузки" }
            loading.value = false
        }
    }

    fun loadNextPage() {
        viewModelScope.launch {
            if (loading.value) return@launch
            loading.value = true
            runCatching { repository.loadNextPage() }
                .onFailure { error.value = it.message ?: "Ошибка загрузки" }
            loading.value = false
        }
    }
}