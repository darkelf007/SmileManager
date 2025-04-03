package com.anaraliev.smilemanager.new_task.domain

import com.anaraliev.smilemanager.database.entity.CustomerPriceItem
import kotlinx.coroutines.flow.Flow

class GetCustomerPricesUseCase(private val repository: CustomerPriceRepository) {
    operator fun invoke(customerId: Int): Flow<List<CustomerPriceItem>> = repository.getPricesForCustomer(customerId)
}