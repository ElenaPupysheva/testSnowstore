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
import com.alonso.testsnowstore.data.ShopItem
import com.alonso.testsnowstore.ui.compose.MainShopListScreen
import com.alonso.testsnowstore.ui.theme.TestSnowstoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TestSnowstoreTheme {
                val items = remember { demoShopItems() }
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

private fun demoShopItems(): List<ShopItem> = listOf(
    ShopItem(
        id = "1",
        model = "Мотоблок Patriot Победа 440",
        description = "Бензиновый мотоблок для вспашки и культивации",
        features = "Двигатель: 7 л.с.\nШирина обработки: 85 см\nГлубина вспашки: 30 см\nВес: 78 кг\nКоробка передач: 2 вперед / 1 назад\nОбъем бака: 3.6 л",
        categories = listOf("Бензиновые", "Самоходные"),
        imageUrl = "https://avatars.mds.yandex.net/get-mpic/7052428/img_id4715440000706980715.jpeg/orig",
        price = 58990,
        editedAt = 1712345678L
    ),
    ShopItem(
        id = "2",
        model = "Культиватор Greenworks GTL9526",
        description = "Электрический культиватор для небольших участков",
        features = "Мощность: 950 Вт\nШирина обработки: 26 см\nГлубина культивации: 19 см\nВес: 12 кг\nУровень шума: 75 дБ\nТип двигателя: электрический",
        categories = listOf("Электрические"),
        imageUrl = "https://avatars.mds.yandex.net/get-mpic/3590777/img_id7037511502978948043.jpeg/orig",
        price = 24990,
        editedAt = 1723456789L
    ),
    ShopItem(
        id = "3",
        model = "Газонокосилка Husqvarna LC 347V",
        description = "Самоходная бензиновая газонокосилка с мульчированием",
        features = "Двигатель: 4.8 л.с.\nШирина скашивания: 47 см\nВысота скашивания: 25-75 мм\nОбъем травосборника: 60 л\nВес: 38 кг\nТип привода: самоходный",
        categories = listOf("Бензиновые", "Самоходные"),
        imageUrl = "https://avatars.mds.yandex.net/get-mpic/5235128/img_id5987983376904828611.jpeg/orig",
        price = 102999,
        editedAt = 1734567890L
    )
)