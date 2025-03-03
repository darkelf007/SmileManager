package com.anaraliev.smilemanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anaraliev.smilemanager.database.dao.CustomerEntityDAO
import com.anaraliev.smilemanager.database.entity.CustomerEntity

@Database(entities = [CustomerEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerEntityDAO(): CustomerEntityDAO
}