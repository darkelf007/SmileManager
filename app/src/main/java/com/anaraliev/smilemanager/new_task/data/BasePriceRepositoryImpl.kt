package com.anaraliev.smilemanager.new_task.data

import com.anaraliev.smilemanager.database.dao.BasePriceDao
import com.anaraliev.smilemanager.database.entity.BasePriceItem
import com.anaraliev.smilemanager.new_task.domain.BasePriceRepository
import kotlinx.coroutines.flow.Flow

class BasePriceRepositoryImpl(private val basePriceDao: BasePriceDao) : BasePriceRepository {
    override fun getAllBasePrices(): Flow<List<BasePriceItem>> {
        return basePriceDao.getAllBasePrices()
    }

    override suspend fun replaceAllBasePrices(items: List<BasePriceItem>) {
        basePriceDao.replaceAllBasePrices(items)
    }
}