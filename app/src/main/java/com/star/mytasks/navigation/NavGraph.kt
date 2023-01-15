package com.star.mytasks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.star.core.extension.orFalse
import com.star.core.navigation.NavigationDirection
import com.star.home.presentation.screen.Home
import com.star.listdetails.presentation.screen.ListDetails
import com.star.mytasks.feature.splash.presentation.SplashScreen

@Composable
fun MainComposeNavigator(
    navController: NavHostController,
    showSplash: () -> Boolean = { true },
    continueShowingSplash: () -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = if (showSplash().orFalse()) {
            NavigationDirection.Splash.route
        } else NavigationDirection.Home.route
    ) {
        if (showSplash().orFalse()) {
            continueShowingSplash()
            composable(route = NavigationDirection.Splash.route) {
                SplashScreen(
                    navController = navController,
                )
            }
        }
        composable(route = NavigationDirection.Home.route) {
            Home(navController)
        }
        composable(route = NavigationDirection.ListDetails.route) { backStackEntry ->
            ListDetails(
                navController = navController,
                title = backStackEntry.arguments?.getString("listTitle").orEmpty()
            )
        }
    }
}