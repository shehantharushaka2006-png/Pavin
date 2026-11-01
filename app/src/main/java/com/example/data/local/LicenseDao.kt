package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.ActivatedLicense
import kotlinx.coroutines.flow.Flow

@Dao
interface LicenseDao {
    @Query("SELECT * FROM activated_licenses ORDER BY activatedTimestamp DESC")
    fun getAllLicenses(): Flow<List<ActivatedLicense>>

    @Query("SELECT * FROM activated_licenses WHERE isActive = 1 ORDER BY expiresTimestamp DESC LIMIT 1")
    fun getActiveLicense(): Flow<ActivatedLicense?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLicense(license: ActivatedLicense)

    @Query("UPDATE activated_licenses SET isActive = 0 WHERE expiresTimestamp < :currentTime")
    suspend fun expireOldLicenses(currentTime: Long)
}
