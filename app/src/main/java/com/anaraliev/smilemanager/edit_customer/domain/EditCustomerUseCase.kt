package com.anaraliev.smilemanager.edit_customer.domain

import com.anaraliev.smilemanager.database.entity.CustomerEntity

interface EditCustomerUseCase {
    suspend fun deleteCustomer(customerEntity: CustomerEntity)
}