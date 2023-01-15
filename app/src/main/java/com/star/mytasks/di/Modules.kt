package com.star.mytasks.di

import com.star.home.di.homeModule
import com.star.listdetails.di.listDetailsModule
import org.koin.core.module.Module

val appModules: List<Module> = listOf(
    homeModule,
    listDetailsModule
)