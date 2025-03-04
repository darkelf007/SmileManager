package com.anaraliev.smilemanager.app

import android.app.Application
import com.anaraliev.smilemanager.di.dataModule
import com.anaraliev.smilemanager.di.databaseModule
import com.anaraliev.smilemanager.di.interactorModule
import com.anaraliev.smilemanager.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(interactorModule, viewModelModule, databaseModule,  dataModule)
        }
    }
}