package com.anaraliev.smilemanager.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.anaraliev.smilemanager.di.databaseModule
import com.anaraliev.smilemanager.di.interactorModule
import com.anaraliev.smilemanager.di.repositoryModule
import com.anaraliev.smilemanager.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@App)
            modules(interactorModule, viewModelModule, databaseModule,repositoryModule)
        }
    }
}