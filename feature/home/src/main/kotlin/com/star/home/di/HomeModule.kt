package com.star.home.di

import androidx.room.Room
import com.star.home.data.datasource.HomeDataSource
import com.star.home.data.datasource.local.HomeLocalDataSource
import com.star.home.data.datasource.local.HomeLocalRoom
import com.star.home.data.repository.HomeRepository
import com.star.home.domain.usecase.SaveHomeStateUseCase
import com.star.home.presentation.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DB_NAME = "home_room_name"

val homeModule = module {
    factory {
        Room.databaseBuilder(
            androidApplication(),
            HomeLocalRoom::class.java,
            DB_NAME
        ).build()
    }
    factory<HomeDataSource> { HomeLocalDataSource(get()) }
    factory { HomeRepository(localDataSource = get()) }
    factory { SaveHomeStateUseCase(homeRepository = get()) }
    viewModel { HomeViewModel(saveHomeState = get()) }
}