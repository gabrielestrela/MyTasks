package com.star.core.extension

fun Boolean?.orFalse() = this ?: false
fun Boolean?.orTrue() = this ?: true