package com.star.listdetails.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.star.core.domain.listdetails.Task
import com.star.core.domain.listdetails.TaskSortType
import com.star.core.domain.listdetails.TaskType
import com.star.listdetails.presentation.event.ListDetailsEvents
import com.star.listdetails.presentation.viewstate.ListDetailsViewState
import de.palm.composestateevents.consumed
import de.palm.composestateevents.triggered
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ListDetailsViewModel : ViewModel() {

    private val _state: MutableStateFlow<ListDetailsViewState> =
        MutableStateFlow(ListDetailsViewState())
    val state = _state.asStateFlow()

    private val _event: MutableStateFlow<ListDetailsEvents> =
        MutableStateFlow(ListDetailsEvents())
    val event = _event.asStateFlow()

    fun onAddTaskClick() {
        _event.update { it.copy(showAddTaskDialog = triggered) }
        _state.update { it.copy(isFabVisible = false) }
    }

    fun onAddTaskComplete() {
        _event.update { it.copy(showAddTaskDialog = consumed) }
    }

    fun showFab() {
        _state.update { it.copy(isFabVisible = true) }
    }

    fun addTask(taskName: String) {
        if (taskName.isNotBlank()) {
            val taskList: MutableList<Task> = _state.value.taskList.toMutableList()
            taskList.add(
                Task(name = taskName)
            )
            _state.update {
                it.copy(
                    taskList = sortByCheckedStatus(taskList),
                    isFabVisible = true
                )
            }
        } else { showFab() }
    }

    fun onCheckBoxToggleClick(isChecked: Boolean, index: Int) {
        val tasks = _state.value.taskList.toMutableList()
        val currentTask = tasks[index]
        tasks[index] = currentTask.copy(
            isChecked = isChecked,
            type = if (isChecked) TaskType.FINISHED else TaskType.UNFINISHED
        )
        _state.update { it.copy(taskList = sortByCheckedStatus(tasks)) }
    }

    private fun sortByCheckedStatus(tasks: List<Task>): List<Task> {
        val finalTaskList: MutableList<Task> = mutableListOf()
        finalTaskList.addAll(tasks.filter { it.type == TaskType.UNFINISHED })

        val completedTasks = tasks.filter { it.type == TaskType.FINISHED }
        if (completedTasks.isNotEmpty()) finalTaskList.add(
            Task(
                type = TaskType.COMPLETE_TITLE
            )
        )
        else finalTaskList.removeIf { it.type == TaskType.COMPLETE_TITLE }
        finalTaskList.addAll(completedTasks)

        return finalTaskList
    }

    fun onTaskDeleteIconClick(index: Int) {
        val tasks = _state.value.taskList.toMutableList()
        tasks.removeAt(index)
        _state.update { it.copy(taskList = sortByCheckedStatus(tasks)) }
    }

    fun sortTasksBy(sortBy: TaskSortType = TaskSortType.NAME) {
        val tasks = _state.value.taskList
        when (sortBy) {
            TaskSortType.NAME -> {
                _state.update { state ->
                    state.copy(
                        taskList = sortByCheckedStatus(tasks.sortedBy { it.name.lowercase() })
                    )
                }
            }
            TaskSortType.CREATED_TIME -> {
                _state.update { state ->
                    state.copy(taskList = sortByCheckedStatus(tasks.sortedBy { it.createdAt }))
                }
            }
        }
    }

    fun clearTasks() {
        _state.update { it.copy(taskList = emptyList()) }
    }
}
