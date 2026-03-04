package com.alonso.testsnowstore.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alonso.testsnowstore.data.ShopItem
import com.alonso.testsnowstore.domain.ShopRepository
import com.alonso.testsnowstore.domain.favourite.FavouritesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class FavouriteUiState(
    val favoriteIds: Set<String> = emptySet()
)

class FavouritesViewModel(
    private val shopRepository: ShopRepository,
    private val favourites: FavouritesRepository
) : ViewModel() {

    val favoriteItems: StateFlow<List<ShopItem>> =
        combine(
            shopRepository.observeItems(),
            favourites.observeFavouriteIdsSet()
        ) { items, favIds ->
            items.filter { it.id in favIds }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    fun onFavoriteClick(id: String) {
        viewModelScope.launch { favourites.toggle(id) }
    }
}