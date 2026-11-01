package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_records")
data class OrderRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val orderRef: String,
    val planName: String,
    val durationDays: Int,
    val priceLkr: Int,
    val gamerIgn: String,
    val gamerUid: String,
    val deviceModel: String,
    val paymentMethod: String,
    val orderTimestamp: Long = System.currentTimeMillis(),
    val status: String = "Pending Verification",
    val customerPhone: String = "075 503 9897"
)
