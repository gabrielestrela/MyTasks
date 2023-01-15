package com.star.home.presentation.composables.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.star.designsystem.R
import com.star.designsystem.spacer.SimpleSpacer
import com.star.designsystem.theme.Background
import com.star.designsystem.theme.Green200
import com.star.designsystem.theme.Green250

@OptIn(ExperimentalUnitApi::class)
@Composable
fun HomeBottomBar(
    onListAddClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .drawBehind {
                drawLine(
                    color = Green200,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 2f
                )
            }
            .clickable { onListAddClick() }
            .background(color = Background)
            .padding(dimensionResource(id = R.dimen.size_16))
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SimpleSpacer(start = dimensionResource(id = R.dimen.size_4))
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(
                id = com.star.feature.home.R.string.add_list_button
            ),
            tint = Green200
        )
        SimpleSpacer(start = dimensionResource(id = R.dimen.size_4))
        Text(
            text = stringResource(id = com.star.feature.home.R.string.add_list_button),
            fontSize = TextUnit(value = 14f, type = TextUnitType.Sp),
            color = Green200
        )
    }
}

@Preview
@Composable
fun HomBottomBarPreview() {
    HomeBottomBar()
}