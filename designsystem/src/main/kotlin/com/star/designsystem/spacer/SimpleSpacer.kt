package com.star.designsystem.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SimpleSpacer(
    start: Dp,
    top: Dp,
    end: Dp,
    bottom: Dp,
) {
    Spacer(
        modifier = Modifier.padding(start = start, top = top, end = end, bottom = bottom)
    )
}

@Composable
fun SimpleSpacer(
    horizontal: Dp,
    vertical: Dp
) {
    Spacer(
        modifier = Modifier.padding(horizontal = horizontal, vertical = vertical)
    )
}

@Composable
fun SimpleSpacer(
    horizontal: Dp? = null,
    vertical: Dp? = null
) {
    Spacer(
        modifier = Modifier.padding(horizontal = horizontal ?: 0.dp, vertical = vertical ?: 0.dp)
    )
}

@Composable
fun SimpleSpacer(
    start: Dp? = null,
    top: Dp? = null,
    end: Dp? = null,
    bottom: Dp? = null,
) {
    Spacer(
        modifier = Modifier.padding(
            start = start ?: 0.dp,
            top = top ?: 0.dp,
            end = end ?: 0.dp,
            bottom = bottom ?: 0.dp
        )
    )
}
