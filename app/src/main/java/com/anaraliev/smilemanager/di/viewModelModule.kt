package com.anaraliev.smilemanager.di


import com.anaraliev.smilemanager.main.MainViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel(get()) }

}