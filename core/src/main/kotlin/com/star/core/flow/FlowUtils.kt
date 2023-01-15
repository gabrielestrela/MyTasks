package com.star.core.flow

import kotlinx.coroutines.channels.Channel

suspend fun <T> Channel<T?>.clearSend(state: T?) {
    this.send(null)
    this.send(state)
}
