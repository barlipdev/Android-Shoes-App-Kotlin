package com.barlipdev.fitrite.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.barlipdev.fitrite.database.FitriteDatabase
import com.barlipdev.fitrite.database.entity.asDomainModel
import com.barlipdev.fitrite.domain.Collection
import com.barlipdev.fitrite.network.FitriteApi
import com.barlipdev.fitrite.network.dto.NetworkCollectionContainer
import com.barlipdev.fitrite.network.dto.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CollectionsRepository(private val database: FitriteDatabase) {

    val collections: LiveData<List<Collection>> = Transformations.map(database.collectionDao.getCollections()){
        it.asDomainModel()
    }

    suspend fun refreshCollections(){
        withContext(Dispatchers.IO){
            val collectionsList = FitriteApi.retrofitService.getCollectionProperties()
            database.collectionDao.insertAll(*NetworkCollectionContainer(collectionsList).asDatabaseModel())
        }
    }

}