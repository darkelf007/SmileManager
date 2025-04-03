package com.anaraliev.smilemanager.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "base_price_items")
data class BasePriceItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workName: String,
    val price: Double
)