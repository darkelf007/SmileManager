package com.anaraliev.smilemanager.new_task.domain

import com.anaraliev.smilemanager.database.entity.CustomerPriceItem

class SaveCustomerPricesUseCase(private val repository: CustomerPriceRepository) {
    suspend operator fun invoke(customerId: Int, items: List<CustomerPriceItem>) = repository.saveAllCustomerPrices(customerId, items)
}