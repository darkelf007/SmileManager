package com.anaraliev.smilemanager.new_customer.domain

interface AddNewCustomerUseCase {
    suspend operator fun invoke(name: String,contactInfo: String?, address: String?, email: String?, percentage: Int?)
}