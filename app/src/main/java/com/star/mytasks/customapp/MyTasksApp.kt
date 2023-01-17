package com.star.mytasks.customapp

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.star.core.BuildConfig
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
        initializeFlipper()
        Timber.plant(getTimberTree())
    }

    private fun getTimberTree(): Timber.Tree =
        if (BuildConfig.DEBUG) Timber.DebugTree() else CrashReportTree()

    private fun initializeFlipper() {
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            with(client) {
                addPlugin(
                    InspectorFlipperPlugin(
                        this@MyTasksApp,
                        DescriptorMapping.withDefaults()
                    )
                )
                addPlugin(DatabasesFlipperPlugin(this@MyTasksApp))
            }
            client.start()
        }
    }

    private val appModules: List<Module> = listOf(
        moshiModule
    )
}