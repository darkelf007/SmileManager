package com.anaraliev.smilemanager.di

import com.anaraliev.smilemanager.customer.data.CustomerRepositoryImpl
import com.anaraliev.smilemanager.customer.domain.CustomerRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CustomerRepository> {
        CustomerRepositoryImpl(get())
    }
}