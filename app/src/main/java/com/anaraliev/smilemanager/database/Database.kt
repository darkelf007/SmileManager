package com.anaraliev.smilemanager.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anaraliev.smilemanager.database.dao.BasePriceDao
import com.anaraliev.smilemanager.database.dao.CustomerEntityDAO
import com.anaraliev.smilemanager.database.dao.CustomerPriceDao
import com.anaraliev.smilemanager.database.entity.BasePriceItem
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.database.entity.CustomerPriceItem

@Database(
    entities = [
        CustomerEntity::class,
        BasePriceItem::class,
        CustomerPriceItem::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerEntityDAO(): CustomerEntityDAO
    abstract fun basePriceDao(): BasePriceDao
    abstract fun customerPriceDao(): CustomerPriceDao
}