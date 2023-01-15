package com.star.home.presentation.event

import de.palm.composestateevents.StateEvent
import de.palm.composestateevents.consumed

data class HomeEvents(
    val showCreateListDialog: StateEvent = consumed
)
