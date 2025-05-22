package com.anaraliev.smilemanager.edit_customer.data

import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.edit_customer.domain.EditCustomerRepository
import com.anaraliev.smilemanager.edit_customer.domain.UpdateCustomerUseCase

class UpdateCustomerUseCaseImpl(
    private val customerRepository: EditCustomerRepository
) : UpdateCustomerUseCase {
    override suspend operator fun invoke(customer: CustomerEntity) {
        customerRepository.updateCustomer(customer)
    }
}