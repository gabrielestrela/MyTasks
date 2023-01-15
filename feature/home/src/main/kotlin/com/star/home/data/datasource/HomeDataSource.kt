package com.star.home.data.datasource

import com.star.home.presentation.viewstate.ListInfo

interface HomeDataSource {
    fun saveHomeData(lists: List<ListInfo>)
}