package com.star.home.domain.mapper

import com.star.home.domain.model.HomeData
import com.star.home.domain.model.ListData
import com.star.home.domain.model.UserData
import com.star.home.presentation.viewstate.HomeViewState

class PresentationToDomainMapper {
    fun mapFrom(state: HomeViewState) = HomeData(
        userData = with(state.userInfo) {
            UserData(
                name = this.name,
                email = this.email,
                pictureUrl = this.pictureUrl
            )
        },
        listData = state.todoLists.map {
            ListData(
                listName = it.name,
                items = it.items,
                uuid = it.uuid,
                createdAt = it.createdAt
            )
        }
    )
}