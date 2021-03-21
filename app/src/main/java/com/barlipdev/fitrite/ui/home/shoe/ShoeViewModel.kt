package com.barlipdev.fitrite.ui.home.shoe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.barlipdev.fitrite.database.getDatabase
import com.barlipdev.fitrite.domain.Brand
import com.barlipdev.fitrite.repository.FitriteRepository
import kotlinx.coroutines.launch


class ShoeViewModel(application: Application,brand: Brand) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val fitriteRepository = FitriteRepository(database)


    init{
        refreshData(brand.idBrand)
    }

    fun refreshData(brandId: String){
        viewModelScope.launch {
            fitriteRepository.refreshShoe(brandId)
        }
    }

    val shoesList = fitriteRepository.getShoesByBrandId(brand.idBrand)

    class Factory(val app: Application, val brand: Brand): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShoeViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return ShoeViewModel(app,brand) as T
            }
            throw IllegalArgumentException("Unbale to construct viewmodel")
        }

    }

}