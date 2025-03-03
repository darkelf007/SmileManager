package com.anaraliev.smilemanager.guide.customer.new_customer.domain

interface CustomerRepository {
    suspend fun addCustomer(name: String)
}
