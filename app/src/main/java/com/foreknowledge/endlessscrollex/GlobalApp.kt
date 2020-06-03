package com.foreknowledge.endlessscrollex

import android.app.Application
import android.content.Context

/**
 * Create by Yeji on 03,June,2020.
 */
class GlobalApp : Application() {
    companion object {
        private lateinit var APP_CONTEXT: Context

        @JvmStatic
        fun getContext(): Context {
            return APP_CONTEXT
        }
    }

    override fun onCreate() {
        super.onCreate()
        APP_CONTEXT = applicationContext
    }
}