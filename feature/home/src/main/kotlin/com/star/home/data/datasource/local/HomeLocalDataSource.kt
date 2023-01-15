package com.star.home.data.datasource.local

import android.content.SharedPreferences
import com.star.home.data.datasource.HomeDataSource
import com.star.home.presentation.viewstate.ListInfo

class HomeLocalDataSource(
    val preferences: SharedPreferences
): HomeDataSource {

    override fun saveHomeData(lists: List<ListInfo>) {
        preferences.edit().putString(
            LISTS_KEY,
            lists.joinToString(separator = SEPARATOR)
        ).apply()
    }

    companion object {
        private const val SEPARATOR = "|_|_|"
        const val LISTS_KEY = "LISTS_KEY"
    }
}