package com.anaraliev.smilemanager.new_customer.domain

interface NewCustomerRepository {
    suspend fun addCustomer(name: String,contactInfo: String?, address: String?, email: String?, percentage: Int?)
}
