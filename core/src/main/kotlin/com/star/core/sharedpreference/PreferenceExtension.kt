package com.star.core.sharedpreference

import android.app.Activity
import androidx.fragment.app.Fragment

fun <T> Fragment.getPreference(key: String, default: T): T {
    val pref by PreferenceDelegate(requireContext(), key, default)
    return pref
}

fun <T> Fragment.setPreference(key: String, value: T, default: T) {
    var pref by PreferenceDelegate(requireContext(), key, default)
    pref = value
}

fun <T> Activity.getPreference(key: String, default: T): T {
    val pref by PreferenceDelegate(this, key, default)
    return pref
}

fun <T> Activity.setPreference(key: String, value: T, default: T) {
    var pref by PreferenceDelegate(this, key, default)
    pref = value
}
