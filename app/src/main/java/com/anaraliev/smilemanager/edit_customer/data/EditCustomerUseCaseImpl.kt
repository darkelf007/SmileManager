package com.anaraliev.smilemanager.edit_customer.data

import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.edit_customer.domain.EditCustomerRepository
import com.anaraliev.smilemanager.edit_customer.domain.EditCustomerUseCase

class EditCustomerUseCaseImpl (private val editCustomerRepository: EditCustomerRepository) :
    EditCustomerUseCase)

{

    override suspend fun deleteCustomer(customerEntity: CustomerEntity) {
        editCustomerRepository.deleteCustomer(customerEntity)
    }

}