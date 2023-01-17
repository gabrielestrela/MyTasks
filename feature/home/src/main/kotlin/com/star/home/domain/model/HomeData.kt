package com.star.home.domain.model

import com.star.core.extension.empty

data class HomeData(
    val userData: UserData = UserData(),
    val listData: List<ListData> = listOf()
)
