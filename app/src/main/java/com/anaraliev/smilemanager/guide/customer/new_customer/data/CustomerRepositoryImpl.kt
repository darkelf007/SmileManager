package com.anaraliev.smilemanager.guide.customer.new_customer.data

import com.anaraliev.smilemanager.database.dao.CustomerEntityDAO
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.guide.customer.new_customer.domain.CustomerRepository

class CustomerRepositoryImpl(
    private val customerDao: CustomerEntityDAO
) : CustomerRepository {
    override suspend fun addCustomer(name: String) {
        val customer = CustomerEntity(customerName = name)
        customerDao.insert(customer)
    }
}