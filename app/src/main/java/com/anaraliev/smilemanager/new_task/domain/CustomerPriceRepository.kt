package com.anaraliev.smilemanager.new_task.domain

import com.anaraliev.smilemanager.database.entity.CustomerPriceItem
import kotlinx.coroutines.flow.Flow

interface CustomerPriceRepository {
    fun getPricesForCustomer(customerId: Int): Flow<List<CustomerPriceItem>>
    suspend fun saveAllCustomerPrices(customerId: Int, items: List<CustomerPriceItem>)
}