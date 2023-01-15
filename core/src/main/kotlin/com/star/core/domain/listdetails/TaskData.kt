package com.star.core.domain.listdetails

import com.star.core.extension.empty
import java.util.*

data class Task(
    val name: String = String.empty(),
    val isChecked: Boolean = false,
    val type: TaskType = TaskType.UNFINISHED,
    val uuid: UUID = UUID.randomUUID(),
    val createdAt: Date = Date()
)

enum class TaskType {
    UNFINISHED,
    FINISHED,
    COMPLETE_TITLE
}
