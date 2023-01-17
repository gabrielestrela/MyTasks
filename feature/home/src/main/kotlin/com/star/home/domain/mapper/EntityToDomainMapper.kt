package com.star.home.domain.mapper

import com.star.core.domain.listdetails.Task
import com.star.core.domain.listdetails.TaskType
import com.star.home.data.model.entity.HomeDataEntity
import com.star.home.domain.model.HomeData
import com.star.home.domain.model.ListData
import com.star.home.domain.model.UserData
import java.util.*

class EntityToDomainMapper {
    fun mapFrom(entity: HomeDataEntity?) = entity?.let {
        HomeData(
            userData = with(entity.userInfo) {
                UserData(
                    name = this.name,
                    email = this.email,
                    pictureUrl = this.pictureUrl
                )
            },
            listData = entity.lists.map { list ->
                ListData(
                    listName = list.name,
                    uuid = UUID.fromString(list.stringUuid),
                    createdAt = Date(list.createdAt),
                    items =  list.items.map { task ->
                        Task(
                            name = task.name,
                            isChecked = task.isChecked,
                            type = TaskType.values()[task.type],
                            uuid = UUID.fromString(task.stringUuid),
                            createdAt = Date(task.createdAt)
                        )
                    }
                )
            }
        )
    }
}