package com.anaraliev.smilemanager.new_customer.data

import com.anaraliev.smilemanager.new_customer.domain.AddNewCustomerUseCase
import com.anaraliev.smilemanager.new_customer.domain.NewCustomerRepository

class AddNewCustomerUseCaseImpl(
    private val newCustomerRepository: NewCustomerRepository
) : AddNewCustomerUseCase {

    override suspend fun invoke(name: String,contactInfo: String?, address: String?, email: String?, percentage: Int?) {
        newCustomerRepository.addCustomer(name,contactInfo,address,email,percentage)
    }
}