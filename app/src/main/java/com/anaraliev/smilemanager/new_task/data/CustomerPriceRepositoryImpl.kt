package com.anaraliev.smilemanager.new_task.data

import androidx.lifecycle.asFlow
import com.anaraliev.smilemanager.database.dao.CustomerPriceDao
import com.anaraliev.smilemanager.database.entity.CustomerPriceItem
import com.anaraliev.smilemanager.new_task.domain.CustomerPriceRepository
import kotlinx.coroutines.flow.Flow

class CustomerPriceRepositoryImpl(private val customerPriceDao: CustomerPriceDao) :
    CustomerPriceRepository {
    override fun getPricesForCustomer(customerId: Int): Flow<List<CustomerPriceItem>> {
        return customerPriceDao.getPricesForCustomer(customerId).asFlow()
    }

    override suspend fun saveAllCustomerPrices(customerId: Int, items: List<CustomerPriceItem>) {
        customerPriceDao.saveAllCustomerPrices(customerId, items)
    }
}