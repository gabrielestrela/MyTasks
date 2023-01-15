package com.star.mytasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.star.core.sharedpreference.PreferenceDelegate
import com.star.mytasks.navigation.MainComposeNavigator
import com.star.designsystem.theme.MyTasksTheme
import com.star.mytasks.di.appModules
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

class MainActivity : ComponentActivity() {

    private var shouldPresentSplash by PreferenceDelegate(
        context = this,
        key = SKIP_SPLASH,
        defaultValue = true,
    )

    private fun injectKoinModules(modules: List<Module> = appModules) {
        unloadKoinModules(modules)
        loadKoinModules(modules)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectKoinModules()
        setContent {
            MyTasksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    MainComposeNavigator(
                        navController = navController,
                        showSplash = { shouldPresentSplash },
                        continueShowingSplash = { shouldPresentSplash = true }
                    )
                }
            }
        }
    }

    private fun setSplashShowFlag(value: Boolean) { shouldPresentSplash = value }

    companion object {
        const val SKIP_SPLASH = "skip_splash"
    }
}
