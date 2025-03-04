package com.anaraliev.smilemanager.new_customer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anaraliev.smilemanager.new_customer.domain.AddNewCustomerUseCase
import kotlinx.coroutines.launch

class NewCustomerViewModel(
    private val addNewCustomerUseCase: AddNewCustomerUseCase
) : ViewModel() {

    fun addCustomer(name: String) {
        viewModelScope.launch {
            addNewCustomerUseCase.invoke(name)
        }
    }
}