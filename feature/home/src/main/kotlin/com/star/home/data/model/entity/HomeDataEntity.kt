package com.star.home.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.star.core.data.listdetails.TaskEntity
import com.star.core.extension.empty
import java.util.*

const val HOME_DATA_ENTITY_TABLE_NAME = "HomeData"

@Entity(tableName = HOME_DATA_ENTITY_TABLE_NAME)
@JsonClass(generateAdapter = true)
data class HomeDataEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: UUID = UUID.randomUUID(),

    @Json(name = "user_info")
    @ColumnInfo("user_info_column")
    val userInfo: UserInfoEntity,

    @Json(name = "lists_info")
    @ColumnInfo("lists_column")
    val lists: List<UserListsEntity>,
)

@JsonClass(generateAdapter = true)
data class UserInfoEntity(
    @Json(name = "user_name")
    val name: String = String.empty(),

    @Json(name = "user_email")
    val email: String = String.empty(),

    @Json(name = "user_picture_url")
    val pictureUrl: String = String.empty()
)

@JsonClass(generateAdapter = true)
data class UserListsEntity(
    @Json(name = "list_name")
    val name: String = String.empty(),

    @Json(name = "task_items")
    val items: List<TaskEntity> = listOf(),

    @Json(name = "uuid_long")
    val stringUuid: String = UUID.randomUUID().toString(),

    @Json(name = "date_long")
    val createdAt: Long = Date().time
)
