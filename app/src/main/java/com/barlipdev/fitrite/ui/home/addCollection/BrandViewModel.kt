package com.barlipdev.fitrite.ui.home.addCollection

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.barlipdev.fitrite.database.getDatabase
import com.barlipdev.fitrite.repository.BrandsRepository
import kotlinx.coroutines.launch


class BrandViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val brandsRepository = BrandsRepository(database)

    init {
        viewModelScope.launch{
            brandsRepository.refreshBrands()
        }
    }

    val brandList = brandsRepository.brands

    class Factory(val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BrandViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return BrandViewModel(app) as T
            }
            throw IllegalArgumentException("Unbale to construct viewmodel")
        }

    }
}