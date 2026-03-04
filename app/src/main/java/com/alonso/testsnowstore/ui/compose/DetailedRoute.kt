package com.alonso.testsnowstore.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alonso.testsnowstore.presentation.detailed.DetailedUiState
import com.alonso.testsnowstore.presentation.detailed.DetailedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailedRoute(
    onBackClick: () -> Unit,
    vm: DetailedViewModel = koinViewModel()
) {
    val state by vm.uiState.collectAsStateWithLifecycle()

    when (val s = state) {
        DetailedUiState.Loading -> Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is DetailedUiState.Error -> Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(s.message)
        }

        is DetailedUiState.Content -> DetailedScreen(
            shopItem = s.item,
            onBackClick = onBackClick,
            onFavoriteClick = { vm.onToggleFavorite() }
        )
    }
}