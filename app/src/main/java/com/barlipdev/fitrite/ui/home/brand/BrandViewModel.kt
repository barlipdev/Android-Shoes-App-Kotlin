package com.barlipdev.fitrite.ui.home.brand

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.barlipdev.fitrite.database.getDatabase
import com.barlipdev.fitrite.domain.Brand
import com.barlipdev.fitrite.repository.FitriteRepository
import kotlinx.coroutines.launch


class BrandViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val brandsRepository = FitriteRepository(database)


    init {
        refreshData()
    }

    private fun refreshData(){
        viewModelScope.launch {
            brandsRepository.refreshBrands()
        }
    }

    val brandList = brandsRepository.brands

    private val _navigateToShoes = MutableLiveData<Brand>()
    val navigateToShoes
        get() = _navigateToShoes

    fun onBrandClicked(brand: Brand){
        _navigateToShoes.value = brand
    }

    fun onShoesNavigated() {
        _navigateToShoes.value = null
    }

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
