package com.anaraliev.smilemanager.new_customer.data

import com.anaraliev.smilemanager.database.dao.CustomerEntityDAO
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.new_customer.domain.NewCustomerRepository

class NewCustomerRepositoryImpl(
    private val customerDao: CustomerEntityDAO
) : NewCustomerRepository {
    override suspend fun addCustomer(name: String,contactInfo: String?, address: String?, email: String?, percentage: Int?) {
        val customer = CustomerEntity(
            customerName = name, contactNumberComments = contactInfo, address = address, email = email, percentage = percentage
        )
        customerDao.insert(customer)
    }
}