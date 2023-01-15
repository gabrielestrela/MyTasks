package com.star.core.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MoshiProvider : MoshiHandler {
    private var moshi: Moshi

    init {
        moshi = getDefaultInstance()
    }

    private fun getDefaultInstance(): Moshi {
        val builder = Moshi.Builder()

        defaultFactories.forEach { builder.add(it) }

        return builder.build()
    }

    override fun getDefaultMoshi(): Moshi = moshi

    override fun getCustomMoshi(factories: Array<JsonAdapter.Factory>): Moshi {
        val filteredFactories: Array<JsonAdapter.Factory> = arrayOf()
        factories.forEach {
            if (defaultFactories.contains(it).not()) filteredFactories.plus(it)
        }

        val newBuilder = moshi.newBuilder()
        filteredFactories.forEach { newBuilder.add(it) }

        return newBuilder.build()
    }

    private val defaultFactories = listOf(
        KotlinJsonAdapterFactory()
    )
}