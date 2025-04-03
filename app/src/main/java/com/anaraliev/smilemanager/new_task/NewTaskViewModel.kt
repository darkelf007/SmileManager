package com.anaraliev.smilemanager.new_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anaraliev.smilemanager.database.entity.CustomerEntity
import com.anaraliev.smilemanager.new_task.domain.NewTaskGetCustomersUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class NewTaskViewModel(
    newTaskGetCustomersUseCase: NewTaskGetCustomersUseCase
) : ViewModel() {

    val customers: StateFlow<List<CustomerEntity>> = newTaskGetCustomersUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

}