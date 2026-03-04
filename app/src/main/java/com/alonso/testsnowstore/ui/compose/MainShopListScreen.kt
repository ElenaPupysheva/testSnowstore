package com.alonso.testsnowstore.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.alonso.testsnowstore.R
import com.alonso.testsnowstore.presentation.main.MainShopViewModel
import com.alonso.testsnowstore.ui.theme.PaddingMedium
import com.alonso.testsnowstore.ui.theme.SpacerThin

@Composable
fun MainShopListScreen(
    navController: NavController,
    viewModel: MainShopViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val textFieldState = viewModel.textFieldState
    Column(
        modifier
            .fillMaxSize()
    ) {
        SimpleSearchBar(
            textFieldState = textFieldState,
            onSearch = viewModel::onSearch,
            searchResults = searchResults,
            modifier = Modifier
                .padding(horizontal = PaddingMedium)
        )

        Spacer(Modifier.height(SpacerThin))

        ShopList(
            shopItemsList = uiState.items,
            onLoadNextPage = viewModel::loadNextPage,
            onItemClick = { item ->
                navController.navigate("details/${item.id}")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchBar(
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    searchResults: List<String>,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
        inputField = {
            SearchBarDefaults.InputField(
                query = textFieldState.text.toString(),
                onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                onSearch = {
                    onSearch(textFieldState.text.toString())
                    expanded = false
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                placeholder = { Text(stringResource(R.string.search)) }
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
            searchResults.forEach { result ->
                ListItem(
                    headlineContent = { Text(result) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            textFieldState.edit { replace(0, length, result) }
                            onSearch(result)
                            expanded = false
                        }
                )
            }
        }
    }
    }
}