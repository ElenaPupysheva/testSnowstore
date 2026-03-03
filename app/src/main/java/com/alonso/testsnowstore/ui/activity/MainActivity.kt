package com.alonso.testsnowstore.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alonso.testsnowstore.ui.compose.NavScreen
import com.alonso.testsnowstore.ui.theme.TestSnowstoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TestSnowstoreTheme {
                NavScreen()
            }
        }
    }
}