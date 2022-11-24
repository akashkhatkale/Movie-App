package com.movee.movee.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application()


fun Main() {
    example {
        println("in lambda")
    }
}



inline fun example(value: () -> Unit) {
    println("hey")
    value()
    println("hey")
}