package com.anaraliev.smilemanager.guide.customer.new_customer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.guide.customer.new_customer.domain.AddCustomerUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NewCustomerViewModel(
    private val addCustomerUseCase: AddCustomerUseCase
) : ViewModel() {

    fun addCustomer(name: String) {
        viewModelScope.launch {
            addCustomerUseCase(name)
        }
    }
}