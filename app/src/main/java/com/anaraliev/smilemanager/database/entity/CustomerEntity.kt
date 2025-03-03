package com.anaraliev.smilemanager.database.entity

import androidx.room.ColumnInfo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,

    @ColumnInfo(name = "customer_name")
    val customerName: String,

    @ColumnInfo(name = "contact_number_comments")
    val contactNumberComments: String? = null,

    @ColumnInfo(name = "address")
    val address: String? = null,

    @ColumnInfo(name = "email")
    val email: String? = null,

    @ColumnInfo(name = "percentage")
    val percentage: String? = null
    )