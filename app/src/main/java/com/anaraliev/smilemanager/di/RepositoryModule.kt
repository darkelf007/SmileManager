package com.anaraliev.smilemanager.di

import com.anaraliev.smilemanager.customer.data.CustomerRepositoryImpl
import com.anaraliev.smilemanager.customer.domain.CustomerRepository
import com.anaraliev.smilemanager.new_customer.data.NewCustomerRepositoryImpl
import com.anaraliev.smilemanager.new_customer.domain.NewCustomerRepository
import com.anaraliev.smilemanager.new_task.data.NewTaskCustomerRepositoryImpl
import com.anaraliev.smilemanager.new_task.domain.NewTaskCustomerRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CustomerRepository> {
        CustomerRepositoryImpl(get())
    }
    single<NewCustomerRepository> { NewCustomerRepositoryImpl(get()) }

    single<NewTaskCustomerRepository> { NewTaskCustomerRepositoryImpl(get()) }
    // Bind other repository interfaces to their implementations
    // Example: single<NewCustomerRepository> { NewCustomerRepositoryImpl(get()) } // Assuming you need this elsewhere
}