package com.alonso.testsnowstore.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.alonso.testsnowstore.ui.compose.MainShopListScreen
import com.alonso.testsnowstore.ui.theme.TestSnowstoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TestSnowstoreTheme {

            val textFieldState = remember { TextFieldState() }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainShopListScreen(
                        shopItems = items,
                        searchResults = emptyList(),
                        textFieldState = textFieldState,
                        onSearch = { /* TODO */ },
                        onLoadNextPage = { /* TODO */ },
                        onItemClick = { /* TODO */ },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}