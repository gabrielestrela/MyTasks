package com.star.home.data.repository

import com.star.home.data.datasource.HomeDataSource
import com.star.home.domain.mapper.DomainToPresentationMapper
import com.star.home.domain.mapper.PresentationToDomainMapper
import com.star.home.presentation.viewstate.HomeViewState

class HomeRepository(
    private val localDataSource: HomeDataSource,
    private val presentationToDomainMapper: PresentationToDomainMapper,
    private val domainToPresentationMapper: DomainToPresentationMapper,
) {
    fun saveHomeData(viewState: HomeViewState) {
        localDataSource.saveHomeData(
            presentationToDomainMapper.mapFrom(viewState)
        )
    }

    fun getHomeData(): HomeViewState? =
        domainToPresentationMapper.mapFrom(localDataSource.getHomeData())
}