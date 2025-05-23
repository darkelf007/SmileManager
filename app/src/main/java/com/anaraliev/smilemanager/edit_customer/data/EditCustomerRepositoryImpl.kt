package com.anaraliev.smilemanager.edit_customer.data

import com.anaraliev.smilemanager.database.dao.CustomerEntityDAO
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.edit_customer.domain.EditCustomerRepository

class EditCustomerRepositoryImpl(private val customerDao: CustomerEntityDAO) : EditCustomerRepository {

    override suspend fun updateCustomer(customer: CustomerEntity) {
        customerDao.update(customer)
    }

    override suspend fun getCustomerById(id: Int): CustomerEntity? {
        return customerDao.getCustomerById(id)
    }

    override suspend fun deleteCustomer(customerEntity: CustomerEntity) {
        customerDao.delete(customerEntity)
    }
}

