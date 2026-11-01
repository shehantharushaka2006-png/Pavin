package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.OrderRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM order_records ORDER BY orderTimestamp DESC")
    fun getAllOrders(): Flow<List<OrderRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderRecord): Long

    @Query("DELETE FROM order_records WHERE id = :id")
    suspend fun deleteOrder(id: Long)
}
