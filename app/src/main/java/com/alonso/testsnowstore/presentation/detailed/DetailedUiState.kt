package com.alonso.testsnowstore.presentation.detailed

import com.alonso.testsnowstore.data.ShopItem

sealed interface DetailedUiState {
    data object Loading : DetailedUiState
    data class Content(val item: ShopItem) : DetailedUiState
    data class Error(val message: String) : DetailedUiState
}