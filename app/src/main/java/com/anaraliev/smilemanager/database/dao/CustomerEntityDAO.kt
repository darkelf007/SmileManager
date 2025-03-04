package com.anaraliev.smilemanager.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CustomerEntityDAO {

    @Delete
    fun delete(customerEntity: CustomerEntity)

    @Insert
    suspend fun insert(customerEntity: CustomerEntity)

    @Query("SELECT * FROM CustomerEntity")
    fun getAllCustomers(): Flow<List<CustomerEntity>>
}