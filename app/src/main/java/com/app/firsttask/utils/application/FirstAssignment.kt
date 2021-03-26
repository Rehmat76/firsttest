package com.app.firsttask.utils.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FirstAssignment : Application() {

    companion object {
        var mInstance: FirstAssignment? = null
    }


    override fun onCreate() {
        super.onCreate()
        mInstance = this
        AppCompatDelegate.MODE_NIGHT_YES

  startKoin {
            androidLogger(Level.NONE)
            androidContext(this@FirstAssignment)
            modules(listOf(appModules, viewModelModules))
        }
    }

    @Synchronized
    fun getInstance(): FirstAssignment? {
        return mInstance
    }

}