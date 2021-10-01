package com.example.healthqrapp.application

import android.app.Application
import android.content.Context


/**
 * Application class is used to get live context throughout the app.
 * Also used to register callbacks at the start of the application.
 *
 * @author Komal Ardekar
 */
class MainApplication : Application() {
    companion object {
        lateinit var instance: MainApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    /**
     * @return The live application context.
     */
    fun getContext(): Context {
        return applicationContext
    }
}