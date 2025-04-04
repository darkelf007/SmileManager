package com.anaraliev.smilemanager.customer.domain

import com.anaraliev.smilemanager.database.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getAllCustomers(): Flow<List<CustomerEntity>>
    suspend fun insertCustomer(customerEntity: CustomerEntity)
    suspend fun deleteCustomer(customerEntity: CustomerEntity)
}