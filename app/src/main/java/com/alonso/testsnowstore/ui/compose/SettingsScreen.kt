package com.alonso.testsnowstore.ui.compose


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.alonso.testsnowstore.App
import com.alonso.testsnowstore.presentation.settings.SettingsViewModel
import com.alonso.testsnowstore.ui.theme.SpacerThin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val context = LocalContext.current
    val app = context.applicationContext as App
    val isDarkTheme by viewModel.darkThemeEnabled.observeAsState(initial = app.darkTheme)

    LaunchedEffect(isDarkTheme) {
        if (app.darkTheme != isDarkTheme) app.switchTheme(isDarkTheme)
    }

    Scaffold(

        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.systemBars
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {
                Spacer(Modifier.height(SpacerThin))
            }


        }
    }
}
