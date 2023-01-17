package com.star.home.domain.mapper

import com.star.core.domain.listdetails.Task
import com.star.home.domain.model.HomeData
import com.star.home.presentation.viewstate.HomeViewState
import com.star.home.presentation.viewstate.ListInfo
import com.star.home.presentation.viewstate.UserInfo

class DomainToPresentationMapper {
    fun mapFrom(data: HomeData?) = data?.let {
        HomeViewState(
            isLoading = false,
            userInfo = with(data.userData) {
                UserInfo(
                    name = this.name,
                    email = this.email,
                    pictureUrl = this.pictureUrl
                )
            },
            todoLists = data.listData.map { list ->
                ListInfo(
                    name = list.listName,
                    items = list.items.map { task ->
                        Task(
                            name = task.name,
                            isChecked = task.isChecked,
                            uuid = task.uuid,
                            createdAt = task.createdAt,
                            type = task.type
                        )
                    },
                    uuid = list.uuid,
                    createdAt = list.createdAt
                )
            }
        )
    }
}