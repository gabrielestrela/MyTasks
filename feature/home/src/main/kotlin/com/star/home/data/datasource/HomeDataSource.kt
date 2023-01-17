package com.star.home.data.datasource

import com.star.home.domain.model.HomeData

interface HomeDataSource {
    fun saveHomeData(homeData: HomeData)
    fun getHomeData(): HomeData?
}