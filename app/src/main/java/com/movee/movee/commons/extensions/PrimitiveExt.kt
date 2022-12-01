package com.movee.movee.commons.extensions


fun Boolean?.orFalse() = this ?: false

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

inline val String.Companion.empty: String get() = ""