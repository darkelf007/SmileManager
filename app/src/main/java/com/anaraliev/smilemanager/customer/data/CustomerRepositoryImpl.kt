package com.anaraliev.smilemanager.customer.data

import com.anaraliev.smilemanager.database.dao.CustomerEntityDAO
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.customer.domain.CustomerRepository
import kotlinx.coroutines.flow.Flow

class CustomerRepositoryImpl(private val customerDao: CustomerEntityDAO) : CustomerRepository {
    override fun getAllCustomers(): Flow<List<CustomerEntity>> = customerDao.getAllCustomers()

    override suspend fun insertCustomer(customerEntity: CustomerEntity) {
        customerDao.insert(customerEntity)
    }

    override suspend fun deleteCustomer(customerEntity: CustomerEntity) {
        customerDao.delete(customerEntity)
    }
}