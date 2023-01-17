package com.star.home.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.star.home.data.model.entity.HomeDataEntity

@Database(entities = [HomeDataEntity::class], version = 2)
@TypeConverters(HomeRoomConverters::class)
abstract class HomeLocalRoom : RoomDatabase() {
    abstract fun homeDataDao(): HomeDao
}
