package com.anaraliev.smilemanager.main


import android.util.Log
import androidx.lifecycle.ViewModel
import com.anaraliev.smilemanager.main.domain.MainInteractor

class MainViewModel(private val interactor: MainInteractor) : ViewModel() {
    init {
        Log.e("MainViewModel", "VM created")
    }

    override fun onCleared() {
        Log.e("MainViewModel", "VM cleared")
        super.onCleared()
    }
}