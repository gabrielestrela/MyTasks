package com.star.designsystem.modifier

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.star.designsystem.R
import com.star.designsystem.theme.BackgroundBrighter

@Composable
fun Modifier.defaultItemModifier(
    cornerRadius: Dp = dimensionResource(id = R.dimen.size_8),
    alpha: Float = 1f
): Modifier {

    val mAlpha: Float by animateFloatAsState(targetValue = alpha)

    return this
        .graphicsLayer(alpha = mAlpha)
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(shape = RoundedCornerShape(cornerRadius))
        .background(color = BackgroundBrighter)
}