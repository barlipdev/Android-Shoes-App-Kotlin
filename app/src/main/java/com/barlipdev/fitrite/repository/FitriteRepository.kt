package com.barlipdev.fitrite.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.barlipdev.fitrite.database.FitriteDatabase
import com.barlipdev.fitrite.database.entity.asDomainModel
import com.barlipdev.fitrite.domain.Brand
import com.barlipdev.fitrite.domain.Shoe
import com.barlipdev.fitrite.network.FitriteApi
import com.barlipdev.fitrite.network.dto.NetworkBrandContainer
import com.barlipdev.fitrite.network.dto.NetworkShoesContainer
import com.barlipdev.fitrite.network.dto.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FitriteRepository(private val database: FitriteDatabase) {

    val brands: LiveData<List<Brand>> = Transformations.map(database.brandDao.getBrands()){
        it.asDomainModel()
    }

    fun getShoesByBrandId(brandId: String): LiveData<List<Shoe>>{
        return Transformations.map(database.shoesDao.getShoesByBrandId(brandId)){
            it.asDomainModel()
        }
    }

    suspend fun refreshBrands() {
        withContext(Dispatchers.IO){
            val brandsList = FitriteApi.retrofitService.getBrandsProperties()
            database.brandDao.insertAll(*NetworkBrandContainer(brandsList).asDatabaseModel())
        }
    }

    suspend fun refreshShoe(brandId: String){
        withContext(Dispatchers.IO){
            val shoesList = FitriteApi.retrofitService.getShoesByBrandId(brandId)
            database.shoesDao.insertAll(*NetworkShoesContainer(shoesList).asDatabaseModel())
        }
    }
}