package com.anaraliev.smilemanager.edit_customer.data

import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.edit_customer.domain.EditCustomerRepository
import com.anaraliev.smilemanager.edit_customer.domain.GetCustomerByIdUseCase

class GetCustomerByIdUseCaseImpl(
    private val customerRepository: EditCustomerRepository
) : GetCustomerByIdUseCase {
    override suspend operator fun invoke(customerId: Int): CustomerEntity? {
        return customerRepository.getCustomerById(customerId)
    }
}