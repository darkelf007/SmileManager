package com.anaraliev.smilemanager.di

import com.anaraliev.smilemanager.customer.data.CustomerRepositoryImpl
import com.anaraliev.smilemanager.customer.domain.CustomerRepository
import com.anaraliev.smilemanager.new_customer.data.NewCustomerRepositoryImpl
import com.anaraliev.smilemanager.new_customer.domain.NewCustomerRepository
import com.anaraliev.smilemanager.new_task.data.BasePriceRepositoryImpl
import com.anaraliev.smilemanager.new_task.data.CustomerPriceRepositoryImpl
import com.anaraliev.smilemanager.new_task.data.NewTaskCustomerRepositoryImpl
import com.anaraliev.smilemanager.new_task.domain.BasePriceRepository
import com.anaraliev.smilemanager.new_task.domain.CustomerPriceRepository
import com.anaraliev.smilemanager.new_task.domain.NewTaskCustomerRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CustomerRepository> {
        CustomerRepositoryImpl(get())
    }
    single<NewCustomerRepository> { NewCustomerRepositoryImpl(get()) }
    single<NewTaskCustomerRepository> { NewTaskCustomerRepositoryImpl(get()) }
    single<BasePriceRepository> { BasePriceRepositoryImpl(get()) }
    single<CustomerPriceRepository> { CustomerPriceRepositoryImpl(get()) }
    single<CustomerRepository> { CustomerRepositoryImpl(get()) }
}