package com.star.core.log

import android.util.Log
import timber.log.Timber

class CrashReportTree : Timber.Tree() {
    override fun log(
        priority: Int,
        tag: String?,
        message: String,
        t: Throwable?
    ) {
        when (priority) {
            Log.ERROR, Log.WARN -> { /* Link Crashlytics */ }
        }
    }
}
