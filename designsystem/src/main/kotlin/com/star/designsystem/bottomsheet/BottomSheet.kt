package com.star.designsystem.bottomsheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import com.star.designsystem.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScaffold(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(dimensionResource(id = R.dimen.size_4)),
    state: ModalBottomSheetState = ModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    ),
    onBackPressed: () -> Unit = {},
    sheetContent: @Composable ColumnScope.() -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    BackHandler(state.isVisible) {
        coroutineScope.launch {
            onBackPressed()
            state.hide()
        }
    }
    
    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = shape,
        sheetContent = sheetContent,
        modifier = Modifier.fillMaxSize(),
        content = content,
    )
}