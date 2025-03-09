package com.anaraliev.smilemanager.di

import com.anaraliev.smilemanager.customer.data.CustomerRepositoryImpl
import com.anaraliev.smilemanager.customer.domain.CustomerRepository
import com.anaraliev.smilemanager.new_customer.data.NewCustomerRepositoryImpl
import com.anaraliev.smilemanager.new_customer.domain.NewCustomerRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CustomerRepository> {
        CustomerRepositoryImpl(get())
    }
    single<NewCustomerRepository> { NewCustomerRepositoryImpl(get()) }
}