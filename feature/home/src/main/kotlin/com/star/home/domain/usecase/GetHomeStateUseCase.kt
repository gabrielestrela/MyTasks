package com.star.home.domain.usecase

import com.star.home.data.repository.HomeRepository
import com.star.home.presentation.viewstate.HomeViewState

class GetHomeStateUseCase(
    private val repository: HomeRepository
) {
    operator fun invoke(): HomeViewState? =
        repository.getHomeData()
}