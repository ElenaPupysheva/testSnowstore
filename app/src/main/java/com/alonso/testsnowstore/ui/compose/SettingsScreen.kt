package com.alonso.testsnowstore.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alonso.testsnowstore.App
import com.alonso.testsnowstore.R
import com.alonso.testsnowstore.presentation.settings.SettingsViewModel
import com.alonso.testsnowstore.ui.theme.SpacerThin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val context = LocalContext.current
    val app = context.applicationContext as App

    val isDarkTheme by viewModel.darkThemeEnabled.observeAsState(initial = app.darkTheme)
    val isEnglish by viewModel.englishLanguageEnabled.observeAsState(initial = (app.langTag.collectAsState().value == "en"))

    LaunchedEffect(isDarkTheme) {
        if (app.darkTheme != isDarkTheme) app.switchTheme(isDarkTheme)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(SpacerThin))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (isDarkTheme) stringResource(R.string.dark_theme)
                else stringResource(R.string.light_theme)
            )
            Switch(
                checked = isDarkTheme,
                onCheckedChange = viewModel::onThemeToggled
            )
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.language_title),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(SpacerThin))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (isEnglish) stringResource(R.string.language_en)
                else stringResource(R.string.language_ru)
            )
            Switch(
                checked = isEnglish,
                onCheckedChange = { enabled ->
                    viewModel.onLanguageToggled(enabled)
                    app.switchLanguage(enabled)
                }
            )
        }
    }
}