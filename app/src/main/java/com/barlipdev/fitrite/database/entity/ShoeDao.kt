package com.barlipdev.fitrite.database.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ShoeDao {
    @Query("select * from shoes where idBrand=:idBrand")
    fun getShoesByBrandId(idBrand: String): LiveData<List<DatabaseShoe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg databaseShoe: DatabaseShoe)
}