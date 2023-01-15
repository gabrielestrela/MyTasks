package com.star.home.presentation.composables.lists

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.star.designsystem.R
import com.star.home.presentation.viewstate.ListInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeLists(
    lists: List<ListInfo> = listOf(),
    modifier: Modifier = Modifier,
    onListClick: (index: Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_2)),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.size_8)), 
    ) {
        itemsIndexed(
            items = lists,
            key = { _, list -> list.uuid },
        ) { index, list ->
            Spacer(
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.size_4))
                    .animateItemPlacement(),
            )
            HomeListItem(
                listData = list,
                modifier = Modifier.animateContentSize().animateItemPlacement(),
                onItemClick = { onListClick(index) }
            )
        }
    }
}
