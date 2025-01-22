package com.anaraliev.smilemanager.di


import com.anaraliev.smilemanager.main.domain.MainInteractor
import com.anaraliev.smilemanager.main.domain.MainUseCase
import org.koin.dsl.module

val interactorModule = module {

    single<MainInteractor> { MainUseCase(get()) }

}