package com.star.core.di

import android.content.Context.MODE_PRIVATE
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DEFAULT_PREF_NAME = "default"

val appModule = module {
    single {
        androidApplication().getSharedPreferences(
            DEFAULT_PREF_NAME,
            MODE_PRIVATE
        )
    }
}