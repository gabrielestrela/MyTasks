package com.star.home.domain.usecase

import com.star.home.data.repository.HomeRepository
import com.star.home.presentation.viewstate.ListInfo

class SaveHomeStateUseCase(
    val homeRepository: HomeRepository
) {
    operator fun invoke(lists: List<ListInfo>) = homeRepository.saveHomeData(lists)
}