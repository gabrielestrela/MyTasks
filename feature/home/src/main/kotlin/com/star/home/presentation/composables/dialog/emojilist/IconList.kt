package com.star.home.presentation.composables.dialog.emojilist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.star.designsystem.spacer.SimpleSpacer
import com.star.home.presentation.viewstate.IconInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IconList(
    onIconClick: (index: Int) -> Unit = {},
    iconList: List<IconInfo> = listOf(
        IconInfo(Icons.Default.List),
        IconInfo(Icons.Default.Check),
        IconInfo(Icons.Default.Edit),
        IconInfo(Icons.Default.Favorite),
        IconInfo(Icons.Default.Notifications),
        IconInfo(Icons.Default.Phone),
        IconInfo(Icons.Default.ShoppingCart),
        IconInfo(Icons.Default.Star),
        IconInfo(Icons.Default.Warning),
        IconInfo(Icons.Default.Person),
        IconInfo(Icons.Default.Place),
        IconInfo(Icons.Default.Call),
        IconInfo(Icons.Default.PlayArrow),
        IconInfo(Icons.Default.Settings),
        IconInfo(Icons.Default.Search),
        IconInfo(Icons.Default.Refresh)
    ),
    modifier: Modifier = Modifier
        .wrapContentWidth()
        .fillMaxHeight(),
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        itemsIndexed(
            items = iconList,
            key = { _, iconInfo -> iconInfo.uuid }
        ) { index, iconInfo ->
            IconListItem(
                index = index,
                iconInfo = iconInfo,
                onIconClick = onIconClick,
                modifier = Modifier
                    .padding(4.dp)
                    .size(32.dp)
                    .animateItemPlacement()
            )
        }
    }
    SimpleSpacer(bottom = 24.dp)
}

@Preview
@Composable
fun IconsListPreview() {
    IconList()
}

@Composable
fun IconListItem(
    index: Int,
    iconInfo: IconInfo,
    onIconClick: (index: Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = { onIconClick(index) },
    ) {
        Icon(
            imageVector = iconInfo.imageVector,
            contentDescription = null,
            tint = iconInfo.color,
            modifier = modifier
        )
    }
}

@Composable
@Preview
fun IconListItemPreview() {
    IconListItem(
        0,
        IconInfo(),
    )
}