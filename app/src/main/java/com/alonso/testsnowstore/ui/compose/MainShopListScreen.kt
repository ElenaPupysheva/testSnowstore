package com.alonso.testsnowstore.ui.compose

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import com.alonso.testsnowstore.data.ShopItem
import com.alonso.testsnowstore.ui.theme.PaddingMedium
import com.alonso.testsnowstore.ui.theme.SpacerThin

@Composable
fun MainShopListScreen(
    shopItems: List<ShopItem>,
    searchResults: List<String>,
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    onLoadNextPage: () -> Unit,
    onItemClick: (ShopItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(Modifier.fillMaxSize()) {
        SimpleSearchBar(
            textFieldState = textFieldState,
            onSearch = onSearch,
            searchResults = searchResults,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingMedium)
        )

        Spacer(Modifier.height(SpacerThin))

        ShopList(
            shopItemsList = shopItems,
            onLoadNextPage = onLoadNextPage,
            onItemClick = onItemClick
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

    SearchBar(
        modifier = modifier.semantics { traversalIndex = 0f },
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
                placeholder = { Text("Search") }
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            searchResults.forEach { result ->
                ListItem(
                    headlineContent = { Text(result) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            textFieldState.edit { replace(0, length, result) }
                            expanded = false
                        }
                )
            }
        }
    }
}