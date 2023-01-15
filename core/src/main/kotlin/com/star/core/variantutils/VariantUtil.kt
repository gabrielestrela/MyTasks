package com.star.core.variantutils

import com.star.core.BuildConfig

inline fun runIfDebug(
    block: () -> Unit = {},
    elseBlock: () -> Unit = {}
) {
    if (BuildConfig.DEBUG) block() else elseBlock()
}