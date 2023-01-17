package com.star.home.domain.mapper

import com.star.core.data.listdetails.TaskEntity
import com.star.home.data.model.entity.HomeDataEntity
import com.star.home.data.model.entity.UserInfoEntity
import com.star.home.data.model.entity.UserListsEntity
import com.star.home.domain.model.HomeData

class DomainToEntityMapper {
    fun mapFrom(homeData: HomeData) = HomeDataEntity(
        userInfo = with(homeData.userData) {
            UserInfoEntity(
                name = this.name,
                email = this.email,
                pictureUrl = this.pictureUrl
            )
        },
        lists = homeData.listData.map { list ->
            UserListsEntity(
                name = list.listName,
                items = list.items.map { item ->
                    TaskEntity(
                        name = item.name,
                        isChecked = item.isChecked,
                        type = item.type.ordinal,
                        stringUuid = item.uuid.toString(),
                        createdAt = item.createdAt.time
                    )
                }
            )
        }
    )
}
