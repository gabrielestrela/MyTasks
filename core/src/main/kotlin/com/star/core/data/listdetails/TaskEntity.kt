package com.star.core.data.listdetails

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.star.core.domain.listdetails.TaskType
import com.star.core.extension.empty
import java.util.*

@JsonClass(generateAdapter = true)
data class TaskEntity(
    @Json(name = "task_name")
    val name: String = String.empty(),

    @Json(name = "is_checked")
    val isChecked: Boolean = false,

    @Json(name = "task_type")
    val type: Int = TaskType.UNFINISHED.ordinal,

    @Json(name = "uuid_string")
    val stringUuid: String = UUID.randomUUID().toString(),

    @Json(name = "date_long")
    val createdAt: Long = Date().time
)