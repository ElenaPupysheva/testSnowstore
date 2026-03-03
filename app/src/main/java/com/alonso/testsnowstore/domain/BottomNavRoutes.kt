package com.alonso.testsnowstore.domain

enum class BottomNavRoutes {
    Main,
    Favourites,
    Settings;

    companion object {
        fun isInEnum(someString: String): Boolean {
            return try {
                valueOf(someString)
                true
            } catch (_: Exception) {
                false
            }
        }
    }
}