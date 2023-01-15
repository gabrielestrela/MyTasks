package com.star.listdetails.presentation.composable.topbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.star.designsystem.theme.BackgroundBrighter
import com.star.designsystem.theme.Gray100
import com.star.feature.listdetails.R


@Composable
fun ListDetailsTopAppBar(
    onSortByDateClick: (() -> Unit)? = null,
    onSortByNameClick: (() -> Unit)? = null,
    onDeleteAllTasks: (() -> Unit)? = null,
    title: String = stringResource(id = R.string.screen_title),
) {
    TopAppBar(
        modifier = Modifier.height(dimensionResource(id = com.star.designsystem.R.dimen.size_72)),
        backgroundColor = BackgroundBrighter,
        title = {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = title,
                color = Gray100
            )
        },
        actions = {
            onSortByDateClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        painter = painterResource(
                            id = com.star.designsystem.R.drawable.ic_sort_by_time_24
                        ),
                        contentDescription = stringResource(
                            id = com.star.designsystem.R.string.sort_tasks_button_created_date_description
                        ),
                        tint = Gray100,
                    )
                }
            }
            onSortByNameClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        painter = painterResource(
                            id = com.star.designsystem.R.drawable.ic_sort_by_alpha_24
                        ),
                        contentDescription = stringResource(
                            id = com.star.designsystem.R.string.sort_tasks_button_description
                        ),
                        tint = Gray100,
                    )
                }
            }
            onDeleteAllTasks?.let {
                IconButton(onClick = onDeleteAllTasks) {
                    Icon(
                        painter = painterResource(
                            id = com.star.designsystem.R.drawable.ic_delete_all_24
                        ),
                        contentDescription = stringResource(
                            id = com.star.designsystem.R.string.delete_all_tasks_button_description
                        ),
                        tint = Gray100,
                    )
                }
            }
        },
    )
}
