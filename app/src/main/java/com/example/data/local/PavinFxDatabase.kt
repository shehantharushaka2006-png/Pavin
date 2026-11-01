package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.ActivatedLicense
import com.example.data.model.OrderRecord

@Database(
    entities = [OrderRecord::class, ActivatedLicense::class],
    version = 1,
    exportSchema = false
)
abstract class PavinFxDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao
    abstract fun licenseDao(): LicenseDao

    companion object {
        @Volatile
        private var INSTANCE: PavinFxDatabase? = null

        fun getInstance(context: Context): PavinFxDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PavinFxDatabase::class.java,
                    "pavin_fx_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
