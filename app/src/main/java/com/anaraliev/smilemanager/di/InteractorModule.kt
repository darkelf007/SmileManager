package com.anaraliev.smilemanager.di


import com.anaraliev.smilemanager.customer.data.CustomerUseCaseImpl
import com.anaraliev.smilemanager.customer.domain.CustomerUseCase
import com.anaraliev.smilemanager.new_customer.domain.AddNewCustomerUseCase
import com.anaraliev.smilemanager.main.domain.MainInteractor
import com.anaraliev.smilemanager.main.domain.MainUseCase
import org.koin.dsl.module

val interactorModule = module {

    single<MainInteractor> { MainUseCase(get()) }
    factory { AddNewCustomerUseCase(get()) }
    single<CustomerUseCase> {
        CustomerUseCaseImpl(get())
    }

}