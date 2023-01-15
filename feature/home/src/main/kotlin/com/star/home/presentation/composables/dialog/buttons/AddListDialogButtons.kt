package com.star.home.presentation.composables.dialog.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.star.core.extension.orFalse
import com.star.designsystem.spacer.SimpleSpacer
import com.star.designsystem.theme.Green200
import com.star.designsystem.theme.Green300
import com.star.designsystem.theme.Green400
import com.star.feature.home.R
import com.star.home.presentation.viewstate.ColorInfo
import com.star.home.presentation.viewstate.ListInfo

@Composable
fun AddListDialogButtons(
    onDismissRequest: () -> Unit = {},
    listInfo: ListInfo = ListInfo(),
    onDoneAction: (listInfo: ListInfo) -> Unit = { },
    isCreateEnabled: (() -> Boolean)? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier.wrapContentSize(),
            contentPadding = PaddingValues(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Green200
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            onClick = { onDismissRequest() }
        ) {
            Text(text = stringResource(
                id = R.string.add_list_dialog_cancel_button)
            )
        }
        SimpleSpacer(start = 2.dp)
        Button(
            modifier = Modifier.wrapContentSize(),
            contentPadding = PaddingValues(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Green200,
                disabledBackgroundColor = Color.Transparent,
                disabledContentColor = Green400
            ),
            elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            onClick = { onDoneAction(listInfo) },
            content = {
                Text(stringResource(id = R.string.add_list_dialog_create_button))
            },
            enabled = isCreateEnabled?.let { it.invoke() }.orFalse()
        )
        SimpleSpacer(
            start = dimensionResource(id = com.star.designsystem.R.dimen.size_8)
        )
    }
}