package com.star.core.coroutines.di

import com.star.core.coroutines.CoroutineDispatcherProvider
import com.star.core.coroutines.DefaultDispatcherProvider
import org.koin.dsl.module

val coroutineCoreModule = module {
    factory<CoroutineDispatcherProvider> { DefaultDispatcherProvider() }
}
