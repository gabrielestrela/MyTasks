package com.star.home.data.datasource.local

import android.content.SharedPreferences
import com.star.home.data.datasource.HomeDataSource
import com.star.home.domain.mapper.DomainToEntityMapper
import com.star.home.domain.mapper.EntityToDomainMapper
import com.star.home.domain.model.HomeData

class HomeLocalDataSource(
    private val roomDao: HomeDao,
    private val domainToEntityMapper: DomainToEntityMapper,
    private val entityToDomainMapper: EntityToDomainMapper
): HomeDataSource {

    override fun saveHomeData(homeData: HomeData) {
        roomDao.saveHomeData(domainToEntityMapper.mapFrom(homeData))
    }

    override fun getHomeData(): HomeData? =
        entityToDomainMapper.mapFrom(roomDao.getHomeData())

    companion object {
        private const val SEPARATOR = "|_|_|"
        const val LISTS_KEY = "LISTS_KEY"
    }
}