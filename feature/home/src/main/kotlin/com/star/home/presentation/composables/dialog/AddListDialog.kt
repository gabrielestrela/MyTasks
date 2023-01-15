package com.star.home.presentation.composables.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.star.core.extension.empty
import com.star.designsystem.spacer.SimpleSpacer
import com.star.designsystem.theme.BackgroundBrighter
import com.star.designsystem.theme.Green200
import com.star.designsystem.theme.Green250
import com.star.feature.home.R
import com.star.home.presentation.composables.dialog.buttons.AddListDialogButtons
import com.star.home.presentation.composables.dialog.colorlist.ColorList
import com.star.home.presentation.composables.dialog.emojilist.IconList
import com.star.home.presentation.composables.dialog.input.ListNameInput
import com.star.home.presentation.viewstate.ColorInfo
import com.star.home.presentation.viewstate.IconInfo
import com.star.home.presentation.viewstate.ListInfo

@Composable
fun AddListDialog(
    isPresent: Boolean = false,
    colorInfoList: List<ColorInfo> = listOf(),
    iconInfoList: List<IconInfo> = listOf(),
    onDismissRequest: () -> Unit = {},
    onDoneAction: (value: ListInfo) -> Unit = {},
    onDialogColorSelect: (index: Int) -> Unit = {},
    onDialogIconSelect: (index: Int) -> Unit = {},
    modifier: Modifier = Modifier.wrapContentHeight()
) {
    var listTitle by remember { mutableStateOf(String.empty()) }
    var selectedColorForList: ColorInfo? by remember { mutableStateOf(null) }
    var animatedColor = animateColorAsState(targetValue =
        colorInfoList.find { it.isSelected }?.color ?: Green250
    )
    var isEmojiViewSelected by rememberSaveable { mutableStateOf(false) }

    if (isPresent) {
        Dialog(
            onDismissRequest = onDismissRequest,
        ) {
            Card (
                modifier = modifier,
                shape = RoundedCornerShape(
                    dimensionResource(id = com.star.designsystem.R.dimen.size_4)
                ),
                backgroundColor = BackgroundBrighter,
                elevation = 8.dp,
            ) {
                Column(modifier = Modifier.background(BackgroundBrighter)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SimpleSpacer(start = 8.dp)
                        Text(
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                            text = stringResource(id = R.string.add_list_dialog_title),
                            color = Green200
                        )
                        SimpleSpacer(end = 8.dp)
                    }
                    SimpleSpacer(top = dimensionResource(id = com.star.designsystem.R.dimen.size_4))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { isEmojiViewSelected = !isEmojiViewSelected }
                        ) {
                            Icon(
                                imageVector = iconInfoList
                                    .find { it.isSelected }
                                    ?.imageVector
                                    ?: Icons.Default.List,
                                contentDescription = stringResource(
                                    id = R.string.select_icon_button_description
                                ),
                                tint = animatedColor.value
                            )
                        }
                        ListNameInput(
                            selectedColor = animatedColor.value,
                            onUpdateAction = { updatedText -> listTitle = updatedText }
                        )
                    }
                    SimpleSpacer(top = 4.dp)

                    AnimatedVisibility(visible = isEmojiViewSelected) {
                        IconList(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(vertical = 16.dp),
                            iconList = iconInfoList,
                            onIconClick = { index ->
                                onDialogIconSelect(index)
                                isEmojiViewSelected = false
                            }
                        )
                    }

                    AnimatedVisibility(
                        visible = isEmojiViewSelected.not(),
                    ) {
                        Column(
                            modifier
                                .fillMaxWidth()
                                .wrapContentHeight()) {
                            SimpleSpacer(top = 4.dp)
                            ColorList(
                                colorInfoList = colorInfoList,
                                onColorSelected = { index, colorInfo ->
                                    selectedColorForList = colorInfo
                                    onDialogColorSelect(index)
                                },
                            )
                            SimpleSpacer(top = 4.dp)
                            AddListDialogButtons(
                                onDismissRequest = onDismissRequest,
                                listInfo = ListInfo(
                                    name = listTitle,
                                    selectedColorInfo = selectedColorForList?.copy(
                                        color = animatedColor.value
                                    ),
                                    selectedIconInfo = iconInfoList.find { it.isSelected }
                                ),
                                onDoneAction = onDoneAction,
                                isCreateEnabled = { listTitle.isNotEmpty() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AddTaskDialogPreview() {
    AddListDialog()
}
