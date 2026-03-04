package com.alonso.testsnowstore.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(timestamp: Long): String {
    return try {
        val date = Date(timestamp * 1000)
        val format = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        format.format(date)
    } catch (e: Exception) {
        "Дата неизвестна"
    }
}
