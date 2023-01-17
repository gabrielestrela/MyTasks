package com.star.home.di

import android.app.Application
import androidx.room.Room
import com.star.home.data.datasource.HomeDataSource
import com.star.home.data.datasource.local.HomeDao
import com.star.home.data.datasource.local.HomeLocalDataSource
import com.star.home.data.datasource.local.HomeLocalRoom
import com.star.home.data.datasource.local.HomeRoomConverters
import com.star.home.data.repository.HomeRepository
import com.star.home.domain.mapper.DomainToEntityMapper
import com.star.home.domain.mapper.DomainToPresentationMapper
import com.star.home.domain.mapper.EntityToDomainMapper
import com.star.home.domain.mapper.PresentationToDomainMapper
import com.star.home.domain.usecase.GetHomeStateUseCase
import com.star.home.domain.usecase.SaveHomeStateUseCase
import com.star.home.presentation.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DB_NAME = "home_room_name"

val homeModule = module {
    factory { HomeRoomConverters() }

    fun provideDataBase(application: Application): HomeLocalRoom {
        return Room.databaseBuilder(
            application,
            HomeLocalRoom::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .addTypeConverter(HomeRoomConverters())
            .build()
    }

    fun provideDao(dataBase: HomeLocalRoom): HomeDao =
        dataBase.homeDataDao()

    factory { provideDataBase(androidApplication()) }
    factory { provideDao(get()) }

    factory { PresentationToDomainMapper() }
    factory { DomainToEntityMapper() }
    factory { DomainToPresentationMapper() }
    factory { EntityToDomainMapper() }

    factory<HomeDataSource> {
        HomeLocalDataSource(
            roomDao = get(),
            domainToEntityMapper = get(),
            entityToDomainMapper = get()
        )
    }
    factory {
        HomeRepository(
            localDataSource = get(),
            presentationToDomainMapper = get(),
            domainToPresentationMapper = get(),
        )
    }
    factory { SaveHomeStateUseCase(homeRepository = get()) }
    factory { GetHomeStateUseCase(repository = get()) }
    viewModel {
        HomeViewModel(
            saveHomeState = get(),
            getHomeState = get(),
            dispatcher = get()
        )
    }
}