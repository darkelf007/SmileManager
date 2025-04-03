package com.anaraliev.smilemanager.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.anaraliev.smilemanager.database.entity.BasePriceItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BasePriceDao {

    @Query("SELECT * FROM base_price_items ORDER BY workName ASC")
    fun getAllBasePrices(): Flow<List<BasePriceItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: BasePriceItem)

    @Update
    suspend fun update(item: BasePriceItem)

    @Delete
    suspend fun delete(item: BasePriceItem)

    @Query("DELETE FROM base_price_items")
    suspend fun deleteAll()

    @Transaction
    suspend fun replaceAllBasePrices(items: List<BasePriceItem>) {
        deleteAll()
        items.forEach { insert(it.copy(id = 0)) }
    }
}