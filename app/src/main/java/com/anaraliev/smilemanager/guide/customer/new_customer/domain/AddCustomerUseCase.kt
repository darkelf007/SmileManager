package com.anaraliev.smilemanager.guide.customer.new_customer.domain

class AddCustomerUseCase(
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(name: String) {
        customerRepository.addCustomer(name)
    }
}