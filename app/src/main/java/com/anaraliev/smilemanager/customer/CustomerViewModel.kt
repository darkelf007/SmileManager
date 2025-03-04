package com.anaraliev.smilemanager.customer

import androidx.lifecycle.ViewModel
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.customer.domain.CustomerUseCase
import kotlinx.coroutines.flow.Flow

class CustomerViewModel(
    private val customerUseCase: CustomerUseCase
) : ViewModel() {

    val customers: Flow<List<CustomerEntity>> = customerUseCase.getAllCustomers()

    suspend fun insertCustomer(customerEntity: CustomerEntity) {
        customerUseCase.insertCustomer(customerEntity)
    }

    suspend fun deleteCustomer(customerEntity: CustomerEntity) {
        customerUseCase.deleteCustomer(customerEntity)
    }
}