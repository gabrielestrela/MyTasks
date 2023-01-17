package com.star.home.domain.usecase

import com.star.home.data.repository.HomeRepository
import com.star.home.presentation.viewstate.HomeViewState
import com.star.home.presentation.viewstate.ListInfo

class SaveHomeStateUseCase(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(state: HomeViewState) = homeRepository.saveHomeData(state)
}