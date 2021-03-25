package com.barlipdev.fitrite.ui.home.shoe

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.barlipdev.fitrite.database.getDatabase
import com.barlipdev.fitrite.domain.Brand
import com.barlipdev.fitrite.domain.Shoe
import com.barlipdev.fitrite.repository.FitriteRepository
import kotlinx.coroutines.launch

class ShoeViewModel(application: Application,brand: Brand) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val fitriteRepository = FitriteRepository(database)

    private val _navigateToShoeSize = MutableLiveData<Shoe>()
    val navigateToShoeSize: LiveData<Shoe>
        get() = _navigateToShoeSize

    init{
        refreshData(brand.idBrand)
    }

    fun refreshData(brandId: String){
        viewModelScope.launch {
                fitriteRepository.refreshShoe(brandId)
        }
    }

    val shoesList = fitriteRepository.getShoesByBrandId(brand.idBrand)

    fun onShoeClicked(shoe: Shoe){
        _navigateToShoeSize.value = shoe
    }

    fun onShoeNavigated(){
        _navigateToShoeSize.value = null
    }


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