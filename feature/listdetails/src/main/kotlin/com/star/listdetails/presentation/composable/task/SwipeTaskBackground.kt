package com.star.listdetails.presentation.composable.task

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.star.designsystem.R
import com.star.designsystem.theme.Deletion
import com.star.designsystem.theme.Gray400
import com.star.designsystem.theme.Gray700
import com.star.designsystem.theme.Green250

private const val SMALL_ICON_SIZE = 1f
private const val DEFAULT_ICON_SIZE = 1.5f

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeTaskBackground(dismissState: DismissState) {
    val color by animateColorAsState(
        targetValue = when (dismissState.targetValue) {
            DismissValue.DismissedToStart -> Deletion
            else -> { Color.Transparent }
        }
    )

    val iconColor by animateColorAsState(
        targetValue = when (dismissState.targetValue) {
            DismissValue.DismissedToStart -> Gray400
            else -> { Green250 }
        }
    )

    val scale by animateFloatAsState(
        targetValue = if (dismissState.targetValue == DismissValue.Default) {
            SMALL_ICON_SIZE
        } else DEFAULT_ICON_SIZE
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_8)))
            .background(color)
            .padding(dimensionResource(id = R.dimen.size_32)),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            Icons.Default.Delete,
            modifier = Modifier.scale(scale),
            tint = iconColor,
            contentDescription = stringResource(
                id = com.star.feature.listdetails.R.string.delete_task_description
            ),
        )
    }
}