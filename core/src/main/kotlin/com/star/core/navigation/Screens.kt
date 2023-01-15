package com.star.core.navigation

sealed class NavigationDirection<T>(
    val name: String,
    val route: String,
    val data: T? = null
) {
    object Splash : NavigationDirection<Any>(
        name = "Splash",
        route = "splash_screen"
    )

    object Home : NavigationDirection<Any>(
        name = "Home",
        route = "home_screen"
    )
    object ListDetails : NavigationDirection<Any>(
        name = "ListDetails",
        route = "list_details_screen/{listTitle}"
    )
}


