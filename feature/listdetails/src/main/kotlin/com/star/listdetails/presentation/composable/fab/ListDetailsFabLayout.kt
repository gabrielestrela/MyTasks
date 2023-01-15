package com.star.listdetails.presentation.composable.fab

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.star.designsystem.theme.Gray100
import com.star.designsystem.theme.Green250
import com.star.feature.listdetails.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ListDetailsFab(
    isVisible: Boolean,
    onClick: () -> Unit = {}
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        FloatingActionButton(
            modifier = Modifier
                .width(dimensionResource(id = com.star.designsystem.R.dimen.size_72))
                .height(dimensionResource(id = com.star.designsystem.R.dimen.size_72))
                .padding(
                    end = dimensionResource(id = com.star.designsystem.R.dimen.size_8),
                    bottom = dimensionResource(id = com.star.designsystem.R.dimen.size_8)
                ),
            onClick = onClick,
            backgroundColor = Green250
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    modifier = Modifier
                        .width(dimensionResource(id = com.star.designsystem.R.dimen.size_40))
                        .height(dimensionResource(id = com.star.designsystem.R.dimen.size_40)),
                    painter = painterResource(id = R.drawable.ic_add_24),
                    contentDescription = "Add list item",
                    tint = Gray100
                )
            }
        }
    }
}