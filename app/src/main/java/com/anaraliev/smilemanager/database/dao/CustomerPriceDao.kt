package com.anaraliev.smilemanager.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.anaraliev.smilemanager.database.entity.CustomerPriceItem

@Dao
interface CustomerPriceDao {

    @Query("SELECT * FROM customer_price_items WHERE customerId = :customerId ORDER BY workName ASC")
    fun getPricesForCustomer(customerId: Int): LiveData<List<CustomerPriceItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CustomerPriceItem)

    @Update
    suspend fun update(item: CustomerPriceItem)

    @Delete
    suspend fun delete(item: CustomerPriceItem)

    @Query("DELETE FROM customer_price_items WHERE customerId = :customerId")
    suspend fun deleteAllForCustomer(customerId: Int)

    @Transaction
    suspend fun saveAllCustomerPrices(customerId: Int, items: List<CustomerPriceItem>) {
        deleteAllForCustomer(customerId)

        items.forEach { insert(it.copy(customerId = customerId)) }
    }
}