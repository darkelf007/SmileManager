package com.anaraliev.smilemanager.di


import com.anaraliev.smilemanager.customer.data.CustomerUseCaseImpl
import com.anaraliev.smilemanager.customer.domain.CustomerUseCase
import com.anaraliev.smilemanager.new_customer.domain.AddNewCustomerUseCase
import com.anaraliev.smilemanager.main.domain.MainInteractor
import com.anaraliev.smilemanager.main.domain.MainUseCase
import com.anaraliev.smilemanager.new_customer.data.AddNewCustomerUseCaseImpl
import com.anaraliev.smilemanager.new_task.domain.GetBasePricesUseCase
import com.anaraliev.smilemanager.new_task.domain.GetCustomerPricesUseCase
import com.anaraliev.smilemanager.new_task.domain.NewTaskGetCustomersUseCase
import com.anaraliev.smilemanager.new_task.domain.SaveBasePricesUseCase
import com.anaraliev.smilemanager.new_task.domain.SaveCustomerPricesUseCase
import org.koin.dsl.module

val interactorModule = module {

    single<MainInteractor> { MainUseCase(get()) }
    single<CustomerUseCase> {
        CustomerUseCaseImpl(get())
    }

    single<AddNewCustomerUseCase> {
        AddNewCustomerUseCaseImpl(get())
    }
    factory { NewTaskGetCustomersUseCase(get()) }
    factory { GetBasePricesUseCase(get()) }
    factory { SaveBasePricesUseCase(get()) }
    factory { GetCustomerPricesUseCase(get()) }
    factory { SaveCustomerPricesUseCase(get()) }
}