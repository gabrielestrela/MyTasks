package com.star.home.presentation.composables.lists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.star.designsystem.R
import com.star.designsystem.modifier.defaultItemModifier
import com.star.designsystem.spacer.SimpleSpacer
import com.star.designsystem.theme.Gray600
import com.star.designsystem.theme.Gray700
import com.star.designsystem.theme.Green250
import com.star.home.presentation.viewstate.ListInfo

@Composable
fun HomeListItem(
    listData: ListInfo = ListInfo(),
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .defaultItemModifier(cornerRadius = dimensionResource(id = R.dimen.size_4))
            .clickable { onItemClick() }
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.size_4))
            .height(dimensionResource(id = R.dimen.size_64)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SimpleSpacer(start = dimensionResource(id = R.dimen.size_4))
        Icon(
            imageVector = listData.selectedIconInfo?.imageVector ?: Icons.Default.List,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.size_24)),
            tint = listData.selectedColorInfo?.color ?: Green250
        )
        SimpleSpacer(start = dimensionResource(id = R.dimen.size_8))
        Text(
            text = listData.name,
            color = Gray600,
        )
        Spacer(Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            modifier = modifier.size(24.dp),
            tint = Gray700
        )
        SimpleSpacer(end = 8.dp)
    }
}

@Preview
@Composable
fun HomeListItemPreview() {
    HomeListItem()
}