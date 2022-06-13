package com.gev.task

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltAndroidApp
class AppApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    companion object {

        private lateinit var weakContext: WeakReference<Context>

        fun getContext(): Context {
            return weakContext.get()!!
        }
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(hiltWorkerFactory)
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build();
    }

    override fun onCreate() {
        super.onCreate()
        // set context
        weakContext = WeakReference(this)
    }
}