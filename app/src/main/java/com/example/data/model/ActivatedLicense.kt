package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activated_licenses")
data class ActivatedLicense(
    @PrimaryKey
    val licenseKey: String,
    val planName: String,
    val durationDays: Int,
    val activatedTimestamp: Long = System.currentTimeMillis(),
    val expiresTimestamp: Long,
    val isActive: Boolean = true
)
