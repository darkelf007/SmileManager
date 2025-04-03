package com.anaraliev.smilemanager.new_task.domain

import com.anaraliev.smilemanager.database.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

interface NewTaskCustomerRepository {
    fun getAllCustomers(): Flow<List<CustomerEntity>>
    suspend fun insertCustomer(customer: CustomerEntity)
    suspend fun deleteCustomer(customer: CustomerEntity)
}
