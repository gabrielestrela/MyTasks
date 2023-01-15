package com.star.home.presentation.composables.dialog.colorlist

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.star.core.extension.orFalse
import com.star.designsystem.R
import com.star.designsystem.spacer.SimpleSpacer
import com.star.home.presentation.viewstate.ColorInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColorList(
    colorInfoList: List<ColorInfo> = listOf(),
    onColorSelected: (index: Int, colorInfo: ColorInfo) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        rows = GridCells.Adaptive(25.dp),
    ) {
        itemsIndexed(
            items = colorInfoList,
            key = { _, color -> color.uuid },
        ) { index, colorInfo ->
            SimpleSpacer(start = 4.dp)
            ColorItem(
                index = index,
                isSelected = colorInfoList[index].isSelected,
                colorInfo = colorInfoList[index],
                onColorSelected = onColorSelected,
                modifier = Modifier.animateItemPlacement()
            )
            SimpleSpacer(end = 4.dp)
        }
    }
}

@Preview @Composable
fun ColorListPreview() { ColorList() }

@Composable
fun ColorItem(
    index: Int,
    isSelected: Boolean,
    colorInfo: ColorInfo = ColorInfo(),
    onColorSelected: (index: Int, colorInfo: ColorInfo) -> Unit = { _, _ -> },
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .clickable { onColorSelected(index, colorInfo) }
            .padding(4.dp)
            .clip(shape = CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.size_32))
        ) { drawCircle(color = colorInfo.color) }
        if (isSelected) {
            Canvas(
                modifier = Modifier.size(6.dp),
            ) {
                drawCircle(Color(255, 255, 255, 130))
            }
        }
    }
}

@Preview
@Composable
fun ColorItemPreview() { ColorItem(0, false) }