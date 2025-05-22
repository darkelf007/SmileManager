package com.anaraliev.smilemanager.edit_customer.domain

import com.anaraliev.smilemanager.database.entity.CustomerEntity

interface GetCustomerByIdUseCase {
    suspend operator fun invoke(customerId: Int): CustomerEntity?
}