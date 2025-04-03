package com.anaraliev.smilemanager.di


import com.anaraliev.smilemanager.customer.CustomerViewModel
import com.anaraliev.smilemanager.new_customer.presentation.NewCustomerViewModel
import com.anaraliev.smilemanager.main.MainViewModel
import com.anaraliev.smilemanager.new_task.NewTaskViewModel
import com.anaraliev.smilemanager.new_task.domain.GetBasePricesUseCase
import com.anaraliev.smilemanager.new_task.domain.GetCustomerPricesUseCase
import com.anaraliev.smilemanager.new_task.domain.NewTaskGetCustomersUseCase
import com.anaraliev.smilemanager.new_task.domain.SaveBasePricesUseCase
import com.anaraliev.smilemanager.new_task.domain.SaveCustomerPricesUseCase

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { NewCustomerViewModel(get()) }
    viewModel { CustomerViewModel(get()) }
    viewModel {
        NewTaskViewModel(
            newTaskGetCustomersUseCase = get<NewTaskGetCustomersUseCase>(),
            getBasePricesUseCase = get<GetBasePricesUseCase>(),
            saveBasePricesUseCase = get<SaveBasePricesUseCase>(),
            getCustomerPricesUseCase = get<GetCustomerPricesUseCase>(),
            saveCustomerPricesUseCase = get<SaveCustomerPricesUseCase>()
        )
    }

}