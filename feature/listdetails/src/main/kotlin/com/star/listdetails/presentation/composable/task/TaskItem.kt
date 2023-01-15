package com.star.listdetails.presentation.composable.task

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.star.core.domain.listdetails.Task
import com.star.designsystem.spacer.SimpleSpacer
import com.star.designsystem.theme.*
import com.star.feature.listdetails.R
import com.star.core.domain.listdetails.TaskType
import com.star.designsystem.modifier.defaultItemModifier

@Composable
fun TaskItem(
    task: Task = Task(),
    taskCount: Int = 0,
    onRadioButtonClick: (Boolean) -> Unit = {},
    onDeleteIconClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {

    val alpha: Float by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(2000)
    )

    if (task.type == TaskType.COMPLETE_TITLE) {
        SimpleSpacer(
            top = dimensionResource(id = com.star.designsystem.R.dimen.size_8)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SimpleSpacer(
                start = dimensionResource(id = com.star.designsystem.R.dimen.size_4)
            )
            Text(
                text = stringResource(
                    id = R.string.completed_task_title,
                    taskCount.toString()
                ),
                color = Green100
            )
        }
        SimpleSpacer(
            bottom = dimensionResource(id = com.star.designsystem.R.dimen.size_8)
        )
    } else {
        Row(
            modifier = Modifier.defaultItemModifier(alpha = alpha)
                .padding(dimensionResource(id = com.star.designsystem.R.dimen.size_16)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                modifier = Modifier.clip(CircleShape),
                checked = task.isChecked,
                onCheckedChange = { isChecked -> onRadioButtonClick(isChecked) },
                colors = CheckboxDefaults.colors(
                    checkedColor = Green300,
                    checkmarkColor = Gray600,
                    uncheckedColor = Green400
                )
            )
            Text(
                color = Gray600,
                text = task.name,
                style = if (task.isChecked) {
                    TextStyle(textDecoration = TextDecoration.LineThrough)
                } else {
                    TextStyle(textDecoration = TextDecoration.None)
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = onDeleteIconClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_remove_24),
                    contentDescription = stringResource(id = R.string.delete_task_description),
                    tint = Green250
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
        }
    }
}

@Composable
@Preview
fun TaskItemPreview() {
    TaskItem()
}
