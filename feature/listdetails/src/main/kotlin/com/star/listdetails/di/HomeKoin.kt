package com.star.listdetails.di

import com.star.listdetails.presentation.viewmodel.ListDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listDetailsModule = module {
    viewModel { ListDetailsViewModel() }
}