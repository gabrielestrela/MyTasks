package com.star.home.presentation.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.star.core.navigation.NavigationDirection
import com.star.designsystem.theme.Background
import com.star.home.presentation.composables.bottombar.HomeBottomBar
import com.star.home.presentation.composables.dialog.AddListDialog
import com.star.home.presentation.composables.lists.HomeLists
import com.star.home.presentation.composables.topbar.HomeTopAppBar
import com.star.home.presentation.viewmodel.HomeViewModel
import com.star.home.presentation.viewstate.HomeViewState
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Home(navController: NavHostController) {
    val viewModel: HomeViewModel = getViewModel()
    val homeState: HomeViewState by viewModel.state.collectAsStateWithLifecycle()
    BackHandler {
        val shouldGoBack =
            navController.backQueue.first().destination.route.orEmpty() !=
                    NavigationDirection.Splash.route

        if (shouldGoBack) navController.navigateUp()
    }

    Scaffold(
        backgroundColor = Background,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeTopAppBar(
                homeState.userInfo,
                onSortByNameClick = viewModel::onSortListsByNameClick,
                onSortByDateClick = viewModel::onSortListsByCreationDate,
                onDeleteAllListsClick = viewModel::onDeleteAllLists
            )
        },
        bottomBar = { HomeBottomBar(onListAddClick = { viewModel.setDialogVisibility(true) }) }
    ) { paddingValues ->

        AddListDialog(
            isPresent = homeState.dialogInfo.shouldDisplayDialog,
            colorInfoList = homeState.dialogInfo.colorInfoList,
            iconInfoList = homeState.dialogInfo.iconInfoList,
            onDismissRequest = {
                viewModel.setDialogVisibility(false)
                viewModel.resetSelectedColor()
            },
            onDoneAction = {
                viewModel.setDialogVisibility(false)
                viewModel.onDialogAddList(it)
                viewModel.resetSelectedIconList()
            },
            onDialogColorSelect = { index -> viewModel.updateSelectedColorInfo(index) },
            onDialogIconSelect = { index -> viewModel.updateSelectedIconInfo(index) }
        )

        HomeLists(
            lists = homeState.todoLists,
            onListClick = { index ->
                navController.navigate(
                    NavigationDirection.ListDetails.route.replace(
                        "{listTitle}", homeState.todoLists[index].name
                    )
                )
            }
        )
    }
}


