package com.score.interview.helperClasses

import android.app.Application

class MyApplication : Application() {

    companion object {
        private var instance: MyApplication? = null

        fun getInstance() = instance ?: throw IllegalStateException("Called getInstance before application was created")
    }

    init {
        instance = this
    }
}