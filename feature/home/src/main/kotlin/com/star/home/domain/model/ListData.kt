package com.star.home.domain.model

import com.star.core.domain.listdetails.Task
import com.star.core.extension.empty
import java.util.*

data class ListData(
    val listName: String = String.empty(),
    val items: List<Task> = listOf(),
    val uuid: UUID = UUID.randomUUID(),
    val createdAt: Date = Date(),
)
