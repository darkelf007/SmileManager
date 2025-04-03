package com.anaraliev.smilemanager.new_task.domain

import com.anaraliev.smilemanager.database.entity.BasePriceItem
import kotlinx.coroutines.flow.Flow

interface BasePriceRepository {
    fun getAllBasePrices(): Flow<List<BasePriceItem>>
    suspend fun replaceAllBasePrices(items: List<BasePriceItem>)
}

class GetBasePricesUseCase(private val repository: BasePriceRepository) {
    operator fun invoke(): Flow<List<BasePriceItem>> = repository.getAllBasePrices()
}

class SaveBasePricesUseCase(private val repository: BasePriceRepository) {
    suspend operator fun invoke(items: List<BasePriceItem>) = repository.replaceAllBasePrices(items)
}