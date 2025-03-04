package com.anaraliev.smilemanager.customer.data

import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.customer.domain.CustomerRepository
import com.anaraliev.smilemanager.customer.domain.CustomerUseCase
import kotlinx.coroutines.flow.Flow

class CustomerUseCaseImpl(private val customerRepository: CustomerRepository) : CustomerUseCase {
    override fun getAllCustomers(): Flow<List<CustomerEntity>> = customerRepository.getAllCustomers()

    override suspend fun insertCustomer(customerEntity: CustomerEntity) {
        customerRepository.insertCustomer(customerEntity)
    }

    override suspend fun deleteCustomer(customerEntity: CustomerEntity) {
        customerRepository.deleteCustomer(customerEntity)
    }
}