package com.star.core.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

interface MoshiHandler {
    fun getDefaultMoshi(): Moshi
    fun getCustomMoshi(factories: Array<JsonAdapter.Factory>): Moshi
}
