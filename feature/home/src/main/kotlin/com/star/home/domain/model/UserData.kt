package com.star.home.domain.model

import com.star.core.extension.empty

data class UserData(
    val name: String = String.empty(),
    val email: String = String.empty(),
    val pictureUrl: String = String.empty()
)
