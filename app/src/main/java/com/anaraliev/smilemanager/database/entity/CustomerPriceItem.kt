package com.anaraliev.smilemanager.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "customer_price_items",
    foreignKeys = [ForeignKey(
        entity = CustomerEntity::class,
        parentColumns = ["uid"],
        childColumns = ["customerId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("customerId")]
)
data class CustomerPriceItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val customerId: Int,
    val workName: String,
    val price: Double
)