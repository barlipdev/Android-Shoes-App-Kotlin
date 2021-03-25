package com.barlipdev.fitrite.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.barlipdev.fitrite.database.getDatabase
import com.barlipdev.fitrite.repository.CollectionsRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class HomeViewModel(application: Application) : AndroidViewModel(application){

    private val _navigateToBrands = MutableLiveData<Boolean>()
    val navigateToBrands: LiveData<Boolean>
        get() = _navigateToBrands

    private val database = getDatabase(application)
    private val collectionsRepository = CollectionsRepository(database)

    init {
        refreshData()
    }

    val properties = collectionsRepository.collections

    private fun refreshData(){
        viewModelScope.launch {
            collectionsRepository.refreshCollections()
        }
    }

    fun onAddButtonClicked(){
        _navigateToBrands.value = true
    }

    fun onBrandsNavigated(){
        _navigateToBrands.value = false
    }

    class Factory(val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
                @Suppress("UCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unbale to construct Viewmodel")
        }

    }

}