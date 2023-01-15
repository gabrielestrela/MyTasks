package com.star.core.moshi.di

import com.star.core.moshi.MoshiHandler
import com.star.core.moshi.MoshiProvider
import org.koin.dsl.module

val moshiModule = module {
    single<MoshiHandler> { MoshiProvider() }
}
