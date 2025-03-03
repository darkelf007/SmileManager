package com.anaraliev.smilemanager.di

import androidx.room.Room
import com.anaraliev.smilemanager.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration().build()
    }

    single { get<AppDatabase>().customerEntityDAO() }
}