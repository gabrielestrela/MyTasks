package com.star.listdetails.presentation.viewstate

import com.star.core.domain.listdetails.Task

data class ListDetailsViewState(
    val isLoading: Boolean = false,
    val taskList: List<Task> = listOf(),
    val isFabVisible: Boolean = true
)
