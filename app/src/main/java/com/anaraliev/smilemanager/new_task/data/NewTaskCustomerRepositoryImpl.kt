package com.anaraliev.smilemanager.new_task.data

import com.anaraliev.smilemanager.database.dao.CustomerEntityDAO
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.new_task.domain.NewTaskCustomerRepository
import kotlinx.coroutines.flow.Flow

class NewTaskCustomerRepositoryImpl(private val customerDao: CustomerEntityDAO) : NewTaskCustomerRepository {

    override fun getAllCustomers(): Flow<List<CustomerEntity>> {
        return customerDao.getAllCustomers()
    }

    override suspend fun insertCustomer(customer: CustomerEntity) {
        customerDao.insert(customer)
    }

    override suspend fun deleteCustomer(customer: CustomerEntity) {
        customerDao.delete(customer)
    }
}