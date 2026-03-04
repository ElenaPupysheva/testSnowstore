package com.alonso.testsnowstore.ui.activity

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.alonso.testsnowstore.App
import com.alonso.testsnowstore.ui.compose.NavScreen
import com.alonso.testsnowstore.ui.theme.TestSnowstoreTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val app = application as App
            val langTag = app.langTag.collectAsStateWithLifecycle().value
            val navController = rememberNavController()

            val localizedContext = remember(langTag) { withLanguage(this, langTag) }

            CompositionLocalProvider(LocalContext provides localizedContext) {
                key(langTag) {
                    TestSnowstoreTheme {
                        NavScreen(navController = navController)
                    }
                }
            }
        }
    }
}

private fun withLanguage(base: Context, tag: String): Context {
    val locale = Locale.forLanguageTag(tag)
    Locale.setDefault(locale)

    val config = Configuration(base.resources.configuration)
    config.setLocale(locale)

    return base.createConfigurationContext(config)
}