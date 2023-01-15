package com.star.home.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.star.home.presentation.viewstate.ListInfo
import com.star.home.presentation.viewstate.UserInfo
import java.util.*

@Entity
data class HomeListsEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo("user_info_column") val userInfo: UserInfo,
    @ColumnInfo("lists_column") val lists: List<ListInfo>,
)
