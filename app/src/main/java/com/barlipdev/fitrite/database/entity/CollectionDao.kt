package com.barlipdev.fitrite.database.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CollectionDao {
    @Query("select * from collection")
    fun getCollections(): LiveData<List<DatabaseCollection>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseCollection: DatabaseCollection)
}