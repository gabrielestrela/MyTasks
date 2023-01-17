package com.star.home.data.datasource.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.star.core.data.listdetails.TaskEntity
import com.star.core.extension.empty
import com.star.core.moshi.MoshiProvider
import com.star.home.data.model.entity.UserInfoEntity
import com.star.home.data.model.entity.UserListsEntity

@ProvidedTypeConverter
class HomeRoomConverters {
    private val moshiProvider = MoshiProvider()
    val moshi: Moshi by lazy { moshiProvider.getDefaultMoshi() }

    @TypeConverter
    fun fromUserEntityInfoToStringJson(
        info: UserInfoEntity
    ): String {
        val adapter = moshi.adapter(UserInfoEntity::class.java)
        return adapter.toJson(info)
    }

    @TypeConverter
    fun fromStringJsonToUserEntity(
        json: String
    ): UserInfoEntity? {
        if (json.isBlank()) return null

        val adapter = moshi.adapter(UserInfoEntity::class.java)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun fromUserListsEntityToStringJson(
        info: List<UserListsEntity>
    ): String {
        if (info.isEmpty()) return String.empty()
        val list = Types.newParameterizedType(
            List::class.java,
            UserListsEntity::class.java
        )

        val adapter = moshi.adapter<List<UserListsEntity>>(list)
        return adapter.toJson(info)
    }

    @TypeConverter
    fun fromStringJsonToUserListsEntity(
        json: String
    ): List<UserListsEntity>? {
        if (json.isBlank()) return null

        val list = Types.newParameterizedType(
            List::class.java,
            UserListsEntity::class.java
        )
        val adapter = moshi.adapter<List<UserListsEntity>>(list)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun fromStringJsonToTasksListEntity(
        json: String
    ): List<TaskEntity>? {
        if (json.isBlank()) return null

        val list = Types.newParameterizedType(
            List::class.java,
            TaskEntity::class.java
        )
        val adapter = moshi.adapter<List<TaskEntity>>(list)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun fromTasksListEntityToJsonString(
        tasks: List<TaskEntity>
    ): String {
        val list = Types.newParameterizedType(
            List::class.java,
            TaskEntity::class.java
        )

        val adapter = moshi.adapter<List<TaskEntity>>(list)
        return adapter.toJson(tasks)
    }

    @TypeConverter
    inline fun <reified T> from(json: String): T? {
        if (json.isBlank()) return null

        val adapter = moshi.adapter(T::class.java)
        return adapter.fromJson(json)
    }

    @TypeConverter
    inline fun <reified T> to(clazz: T): String {
        val adapter = moshi.adapter(T::class.java)
        return adapter.toJson(clazz)
    }
}