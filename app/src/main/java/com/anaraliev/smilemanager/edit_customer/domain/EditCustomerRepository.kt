package com.anaraliev.smilemanager.edit_customer.domain

import com.anaraliev.smilemanager.database.entity.CustomerEntity

interface EditCustomerRepository{
    suspend fun updateCustomer(customer: CustomerEntity)
    suspend fun getCustomerById(id: Int): CustomerEntity?
}