package com.star.mytasks.customapp

import android.app.Application
import com.star.core.BuildConfig
import com.star.core.coroutines.di.coroutineCoreModule
import com.star.core.di.appModule
import com.star.core.log.CrashReportTree
import com.star.core.moshi.di.moshiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import timber.log.Timber

class MyTasksApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyTasksApp)
            modules(appModules)
        }

        Timber.plant(getTimberTree())
    }

    private fun getTimberTree(): Timber.Tree =
        if (BuildConfig.DEBUG) Timber.DebugTree() else CrashReportTree()

    private val appModules: List<Module> = listOf(
        appModule,
        moshiModule,
        coroutineCoreModule
    )
}