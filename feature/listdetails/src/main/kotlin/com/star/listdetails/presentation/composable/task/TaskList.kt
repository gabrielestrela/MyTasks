package com.star.listdetails.presentation.composable.task

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.star.core.domain.listdetails.Task
import com.star.designsystem.R
import com.star.designsystem.spacer.SimpleSpacer
import com.star.designsystem.theme.Deletion
import com.star.core.domain.listdetails.TaskType
import com.star.core.domain.listdetails.TaskType.FINISHED
import com.star.core.domain.listdetails.TaskType.UNFINISHED
import com.star.designsystem.animations.ANIMATION_TIME
import com.star.listdetails.presentation.model.SwipeActionConfig

private const val COMPLETED_TASKS_TITLE_ID = "completed_tasks_title_id"
private const val CHECK_THRESHOLD = 0.4f

private val swipeToStart = SwipeActionConfig(
    threshold = 0.4f,
    icon = Icons.Default.Delete,
    iconTint = Color.Black,
    background = Deletion,
    stayDismissed = true,
    onDismiss = {}
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun TaskList(
    onTaskCheck: (isChecked: Boolean, index: Int) -> Unit,
    onTaskDelete: (index: Int) -> Unit,
    tasks: List<Task> = listOf()
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.size_2)),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.size_8)),
    ) {
        itemsIndexed(
            items = tasks,
            key = { _, task ->
                if (task.type == TaskType.COMPLETE_TITLE) {
                    COMPLETED_TASKS_TITLE_ID
                } else task.uuid
            },
        ) { index, task ->
            when (task.type) {
                UNFINISHED, FINISHED -> {
                    val currentTaskIndex by rememberUpdatedState(newValue = index)
                    val dismissState = rememberDismissState(
                        confirmStateChange = { dismissValue ->
                            when (dismissValue) {
                                DismissValue.DismissedToStart -> {
                                    onTaskDelete(currentTaskIndex)
                                    true
                                }
                                else -> false
                            }
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        background = { SwipeTaskBackground(dismissState) },
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { direction ->
                            FractionalThreshold(
                                if (direction == DismissDirection.StartToEnd) {
                                    CHECK_THRESHOLD
                                } else CHECK_THRESHOLD
                            )
                        },
                        modifier = Modifier.animateItemPlacement()
                    ) {
                        TaskItem(
                            task = task,
                            onRadioButtonClick = { isChecked -> onTaskCheck(isChecked, index) },
                            onDeleteIconClick = { onTaskDelete(index) },
                            modifier = Modifier
                                .animateContentSize()
                                .animateItemPlacement(
                                    animationSpec = tween(durationMillis = ANIMATION_TIME)
                                )
                        )
                    }
                    if (index != tasks.lastIndex) {
                        SimpleSpacer(
                            bottom = dimensionResource(id = R.dimen.size_4)
                        )
                    }
                }
                TaskType.COMPLETE_TITLE -> {
                    TaskItem(
                        task = task,
                        modifier = Modifier
                            .animateContentSize()
                            .animateItemPlacement(
                                animationSpec = tween(durationMillis = ANIMATION_TIME/2)
                            ),
                        taskCount = tasks.filter { it.isChecked }.count()
                    )
                }
            }
        }
    }
}