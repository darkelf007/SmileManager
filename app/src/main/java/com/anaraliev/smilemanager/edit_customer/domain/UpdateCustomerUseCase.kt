package com.anaraliev.smilemanager.edit_customer.domain

import com.anaraliev.smilemanager.database.entity.CustomerEntity

interface UpdateCustomerUseCase {
    suspend operator fun invoke(customer: CustomerEntity)
}