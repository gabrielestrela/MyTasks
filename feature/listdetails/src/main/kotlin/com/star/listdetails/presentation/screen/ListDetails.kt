package com.star.listdetails.presentation.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.star.core.domain.listdetails.Task
import com.star.core.navigation.NavigationDirection
import com.star.designsystem.bottomsheet.BottomSheetScaffold
import com.star.designsystem.theme.Background
import com.star.listdetails.presentation.bottomdialog.AddTaskBottomDialogLayout
import com.star.listdetails.presentation.composable.fab.ListDetailsFab
import com.star.listdetails.presentation.composable.task.TaskList
import com.star.core.domain.listdetails.TaskSortType
import com.star.listdetails.presentation.composable.topbar.ListDetailsTopAppBar
import com.star.listdetails.presentation.event.ListDetailsEvents
import com.star.listdetails.presentation.viewmodel.ListDetailsViewModel
import com.star.listdetails.presentation.viewstate.ListDetailsViewState
import de.palm.composestateevents.EventEffect
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun ListDetails(
    navController: NavHostController,
    title: String
) {
    val viewModel: ListDetailsViewModel = getViewModel()
    val sheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = { value ->
                value != ModalBottomSheetValue.Hidden
            }
        )

    val homeState: ListDetailsViewState by viewModel.state.collectAsStateWithLifecycle()

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
            ListDetailsTopAppBar(
                onSortByDateClick = { viewModel.sortTasksBy(TaskSortType.CREATED_TIME) },
                onSortByNameClick = { viewModel.sortTasksBy() },
                onDeleteAllTasks = { viewModel.clearTasks() },
                title = title
            )
        },
        floatingActionButton = {
            ListDetailsFab(homeState.isFabVisible) { viewModel.onAddTaskClick() }
        }
    ) { _ ->
        val event: ListDetailsEvents by viewModel.event.collectAsStateWithLifecycle()
        ListDetailsContent(
            sheetState = sheetState,
            event = event,
            tasks = homeState.taskList,
            onAddTask = viewModel::addTask,
            showFab = viewModel::showFab,
            onAddTaskComplete = viewModel::onAddTaskComplete,
            onDeleteIconClick = viewModel::onTaskDeleteIconClick,
            onCheckBoxToggle = viewModel::onCheckBoxToggleClick,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListDetailsContent(
    sheetState: ModalBottomSheetState,
    event: ListDetailsEvents,
    tasks: List<Task>,
    onAddTask: (inputText: String) -> Unit = {},
    showFab: () -> Unit = {},
    onAddTaskComplete: () -> Unit = {},
    onDeleteIconClick: (index: Int) -> Unit = {},
    onCheckBoxToggle: (isChecked: Boolean, index: Int) -> Unit,
) {
    BottomSheetScaffold(
        sheetContent = {
            AddTaskBottomDialogLayout(sheetState) { inputText ->
                onAddTask(inputText)
            }
        },
        onBackPressed = { showFab() },
        state = sheetState,
    ) {
        EventEffect(
            event = event.showAddTaskDialog,
            onConsumed = onAddTaskComplete,
        ) {
            if (sheetState.isVisible) {
                sheetState.hide()
                showFab()
            }
            else sheetState.show()
        }

        TaskList(
            tasks = tasks,
            onTaskCheck = { isChecked, index -> onCheckBoxToggle(isChecked, index) },
            onTaskDelete = { index -> onDeleteIconClick(index) }
        )
    }
}
