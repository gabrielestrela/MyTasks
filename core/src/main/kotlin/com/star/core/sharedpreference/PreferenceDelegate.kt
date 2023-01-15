package com.star.core.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private const val DEFAULT_PREF_NAME = "default"
private const val TYPE_ERROR_GET_MESSAGE = "Cannot get this type from preferences"
private const val TYPE_ERROR_SET_MESSAGE = "Cannot set this type to preferences"

class PreferenceDelegate<T>(
    private val context: Context,
    private val key: String,
    private val defaultValue: T,
    private val preferenceName: String = DEFAULT_PREF_NAME
) : ReadWriteProperty<Any?, T> {

    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T =
        getPreference(key, defaultValue)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        setPreference(key, value)
    }

    private fun getPreference(key: String, defaultValue: T): T = with(preferences) {
        val res = when (defaultValue) {
            is Long -> getLong(key, defaultValue)
            is String -> getString(key, defaultValue)
            is Int -> getInt(key, defaultValue)
            is Boolean -> getBoolean(key, defaultValue)
            is Float -> getFloat(key, defaultValue)
            else -> throw IllegalArgumentException(TYPE_ERROR_GET_MESSAGE)
        }

        @Suppress("UNCHECKED_CAST")
        return res as T
    }

    private fun setPreference(key: String, value: T) = with(preferences.edit()) {
        when (value) {
            is Long -> putLong(key, value)
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            else -> throw IllegalArgumentException(TYPE_ERROR_SET_MESSAGE)
        }
    }.apply()
}
