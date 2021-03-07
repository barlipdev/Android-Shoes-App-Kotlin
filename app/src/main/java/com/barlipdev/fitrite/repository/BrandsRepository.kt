package com.barlipdev.fitrite.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.barlipdev.fitrite.database.FitriteDatabase
import com.barlipdev.fitrite.database.entity.DatabaseBrand
import com.barlipdev.fitrite.database.entity.asDomainModel
import com.barlipdev.fitrite.domain.Brand
import com.barlipdev.fitrite.network.FitriteApi
import com.barlipdev.fitrite.network.dto.NetworkBrandContainer
import com.barlipdev.fitrite.network.dto.asDatabaseModel
import com.barlipdev.fitrite.network.dto.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BrandsRepository(private val database: FitriteDatabase) {

    val brands: LiveData<List<Brand>> = Transformations.map(database.brandDao.getBrands()){
        it.asDomainModel()
    }

    suspend fun refreshBrands() {
        withContext(Dispatchers.IO){
            val brandsList = FitriteApi.retrofitService.getBrandsProperties()
            database.brandDao.insertAll(*NetworkBrandContainer(brandsList).asDatabaseModel())
        }
    }
}