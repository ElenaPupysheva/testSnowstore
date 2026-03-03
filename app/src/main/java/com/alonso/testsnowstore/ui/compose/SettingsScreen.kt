package com.alonso.testsnowstore.ui.compose


import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.alonso.testsnowstore.App
import com.alonso.testsnowstore.presentation.settings.SettingsViewModel
import com.alonso.testsnowstore.ui.theme.SpacerThin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val context = LocalContext.current
    val app = context.applicationContext as App
    val isDarkTheme by viewModel.darkThemeEnabled.observeAsState(initial = app.darkTheme)
    val isEnglish by viewModel.englishLanguageEnabled.observeAsState(initial = false)
    LaunchedEffect(isDarkTheme) {
        if (app.darkTheme != isDarkTheme) app.switchTheme(isDarkTheme)
    }
    LaunchedEffect(isEnglish) {
        val locales = if (isEnglish) {
            LocaleListCompat.forLanguageTags("en")
        } else {
            LocaleListCompat.forLanguageTags("ru")
        }
        AppCompatDelegate.setApplicationLocales(locales)
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(if (isDarkTheme) "Dark" else "Light")
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = viewModel::onThemeToggled
                )
            }

            Spacer(Modifier.height(24.dp))

            Text("Language", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(SpacerThin))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(if (isEnglish) "English" else "Русский")
                Switch(
                    checked = isEnglish,
                    onCheckedChange = viewModel::onLanguageToggled
                )
            }

        }
    }
}
