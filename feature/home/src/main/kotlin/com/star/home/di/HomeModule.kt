package com.star.home.di

import com.star.home.data.datasource.HomeDataSource
import com.star.home.data.datasource.local.HomeLocalDataSource
import com.star.home.data.repository.HomeRepository
import com.star.home.domain.usecase.SaveHomeStateUseCase
import com.star.home.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory<HomeDataSource> { HomeLocalDataSource(get()) }
    factory { HomeRepository(localDataSource = get()) }
    factory { SaveHomeStateUseCase(homeRepository = get()) }
    viewModel { HomeViewModel(saveHomeState = get()) }
}