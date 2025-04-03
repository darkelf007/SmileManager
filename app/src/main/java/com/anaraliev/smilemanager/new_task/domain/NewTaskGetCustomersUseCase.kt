package com.anaraliev.smilemanager.new_task.domain

import com.anaraliev.smilemanager.database.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

class NewTaskGetCustomersUseCase(private val newTaskCustomerRepository: NewTaskCustomerRepository) {
    operator fun invoke(): Flow<List<CustomerEntity>> {
        return newTaskCustomerRepository.getAllCustomers()
    }
}