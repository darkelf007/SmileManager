package com.anaraliev.smilemanager.new_task

import com.anaraliev.smilemanager.database.entity.CustomerEntity

interface CustomerSelectionListener {
    /**
     * Called when a customer item is selected.
     * @param customer The selected CustomerEntity.
     */
    fun onCustomerSelected(customer: CustomerEntity)
}