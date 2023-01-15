package com.star.mytasks.feature.splash.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.star.core.navigation.NavigationDirection
import com.star.mytasks.R

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.mytasks_splash_lottie_animation)
        )
        val animationState = animateLottieCompositionAsState(composition = composition)

        LottieAnimation(
            composition = composition,
            progress = { animationState.progress },
            modifier = Modifier.align(Alignment.Center).width(140.dp).height(140.dp),
            contentScale = ContentScale.Fit
        )


        if (animationState.isAtEnd && animationState.isPlaying) {
            navController.navigate(NavigationDirection.Home.route)
        }
    }
}