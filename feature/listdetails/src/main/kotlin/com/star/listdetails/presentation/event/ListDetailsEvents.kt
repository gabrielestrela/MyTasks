package com.star.listdetails.presentation.event

import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.consumed

data class ListDetailsEvents(
    val showAddTaskDialog : StateEvent = consumed
)
