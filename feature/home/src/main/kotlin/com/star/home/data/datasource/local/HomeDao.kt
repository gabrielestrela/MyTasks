package com.star.home.data.datasource.local

import androidx.room.*
import com.star.home.data.model.entity.HOME_DATA_ENTITY_TABLE_NAME
import com.star.home.data.model.entity.HomeDataEntity

@Dao
interface HomeDao {
    @Query("SELECT * FROM $HOME_DATA_ENTITY_TABLE_NAME")
    fun getHomeData(): HomeDataEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveHomeData(data: HomeDataEntity)

    @Delete
    fun deleteHomeData(data: HomeDataEntity)
}
