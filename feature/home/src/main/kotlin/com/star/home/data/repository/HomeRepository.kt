package com.star.home.data.repository

import com.star.home.data.datasource.HomeDataSource
import com.star.home.presentation.viewstate.ListInfo
import java.util.*

class HomeRepository(
    val localDataSource: HomeDataSource
) {
    fun saveHomeData(lists: List<ListInfo>) {
        localDataSource.saveHomeData(lists)
    }
}