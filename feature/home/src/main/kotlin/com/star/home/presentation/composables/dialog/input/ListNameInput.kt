package com.star.home.presentation.composables.dialog.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.star.core.extension.empty
import com.star.designsystem.R
import com.star.designsystem.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListNameInput(
    selectedColor: Color? = null,
    onUpdateAction: (value: String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var inputValue by remember {
        mutableStateOf(String.empty())
    }

    TextField(
        placeholder = { Text(stringResource(id = com.star.feature.home.R.string.add_list_dialog_input_placeholder)) },
        value = inputValue,
        onValueChange = {
            inputValue = it
            onUpdateAction(it)
        },
        modifier = modifier
            .fillMaxWidth(.9f)
            .height(dimensionResource(id = R.dimen.size_64)),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Gray200,
            backgroundColor = Color.Transparent,
            placeholderColor = Gray700,
            cursorColor = selectedColor ?: Green300,
            focusedIndicatorColor = selectedColor ?: Green250, // <-- needs to change
            unfocusedIndicatorColor = selectedColor ?: Green250, // needs to change
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            },
        ),
    )
}

@Preview
@Composable
fun ListNameInputPreview() {
    ListNameInput()
}