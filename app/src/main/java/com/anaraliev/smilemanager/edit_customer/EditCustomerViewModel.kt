package com.anaraliev.smilemanager.edit_customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.edit_customer.domain.EditCustomerUseCase
import com.anaraliev.smilemanager.edit_customer.domain.GetCustomerByIdUseCase
import com.anaraliev.smilemanager.edit_customer.domain.UpdateCustomerUseCase
import kotlinx.coroutines.launch

class EditCustomerViewModel(
    private val getCustomerByIdUseCase: GetCustomerByIdUseCase,
    private val updateCustomerUseCase: UpdateCustomerUseCase
) : ViewModel() {

    private val _customer = MutableLiveData<CustomerEntity?>()
    val customer: LiveData<CustomerEntity?> = _customer

    private val _updateResult = MutableLiveData<Result<Unit>>()
    val updateResult: LiveData<Result<Unit>> = _updateResult


    fun loadCustomer(customerId: Int) {
        viewModelScope.launch {
            try {
                _customer.value = getCustomerByIdUseCase(customerId)
            } catch (e: Exception) {

                _customer.value = null
            }
        }
    }

    fun updateCustomer(
        uid: Int,
        name: String,
        contactInfo: String?,
        address: String?,
        email: String?,
        percentage: Int?
    ) {
        val customerToUpdate = CustomerEntity(
            uid = uid,
            customerName = name,
            contactNumberComments = contactInfo,
            address = address,
            email = email,
            percentage = percentage
        )
        viewModelScope.launch {
            try {
                updateCustomerUseCase(customerToUpdate)
                _updateResult.value = Result.success(Unit)
            } catch (e: Exception) {
                _updateResult.value = Result.failure(e)
            }
        }
    }

    suspend fun deleteCustomer(customerEntity: CustomerEntity) {
        EditCustomerUseCase.deleteCustomer(customerEntity)
    }
}