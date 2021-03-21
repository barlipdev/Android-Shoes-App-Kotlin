package com.barlipdev.fitrite.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.barlipdev.fitrite.database.entity.*

@Database(entities = [DatabaseBrand::class,DatabaseCollection::class,DatabaseShoe::class], version = 1)
abstract class FitriteDatabase : RoomDatabase() {
    abstract val brandDao : BrandDao
    abstract val collectionDao: CollectionDao
    abstract val shoesDao: ShoeDao
}

private lateinit var INSTANCE : FitriteDatabase

fun getDatabase(context: Context) : FitriteDatabase {
    synchronized(FitriteDatabase::class.java){
        if (!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                FitriteDatabase::class.java,
                "Fitrite").build()
        }
    }
    return INSTANCE
}