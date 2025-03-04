package com.anaraliev.smilemanager.new_customer.domain

class AddNewCustomerUseCase(
    private val newCustomerRepository: NewCustomerRepository
) {
    suspend operator fun invoke(name: String) {
        newCustomerRepository.addCustomer(name)
    }
}