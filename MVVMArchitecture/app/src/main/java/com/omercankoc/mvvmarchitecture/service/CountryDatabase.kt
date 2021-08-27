package com.omercankoc.mvvmarchitecture.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omercankoc.mvvmarchitecture.model.Country

@Database(entities = arrayOf(Country::class),version = 1)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao() : CountryDao

    // Singleton : Icerisinden tek bir obje olusturan yapi.
    companion object {
        @Volatile private var instance : CountryDatabase? = null

        private var lock = Any()

        // Instance var ise dondur, yok ise sync olarak olustur ve dondur.
        operator fun invoke(context : Context) = instance ?: synchronized(lock) {
            // Veri tabanini olustur.
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        // DB Room yardimi ile olustur.
        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,CountryDatabase::class.java,"countryDB"
        ).build()
    }
}