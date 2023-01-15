package com.star.listdetails.presentation.bottomdialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.star.core.extension.empty
import com.star.designsystem.R
import com.star.designsystem.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AddTaskBottomDialogLayout(
    dialogState: ModalBottomSheetState,
    onDoneAction: (inputText: String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var inputValue by rememberSaveable { mutableStateOf(String.empty()) }

    Row(
       modifier = Modifier.fillMaxWidth(),
       horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.size_64))
                .focusRequester(focusRequester),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Gray300,
                backgroundColor = BackgroundBrighter,
                placeholderColor = Gray700,
                cursorColor = Green300,
                focusedIndicatorColor = Green250,
                unfocusedIndicatorColor = Green400,
            ),
            placeholder = {
                Text(text = stringResource(
                    id = com.star.feature.listdetails.R.string.task_input_placeholder)
                )
            },
            value = inputValue,
            onValueChange = { newValue ->
                inputValue = newValue
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    coroutineScope.launch { dialogState.hide() }
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    onDoneAction(inputValue)
                    inputValue = String.empty()
                }
            )
        )
    }

    LaunchedEffect(Unit) {
        if (dialogState.isVisible) focusRequester.requestFocus()
    }
}