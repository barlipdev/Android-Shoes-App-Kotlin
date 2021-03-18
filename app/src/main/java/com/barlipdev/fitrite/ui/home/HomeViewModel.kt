package com.barlipdev.fitrite.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.barlipdev.fitrite.database.getDatabase
import com.barlipdev.fitrite.repository.CollectionsRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

enum class FitriteApiStatus { LOADING, ERROR, DONE }

class HomeViewModel(application: Application) : AndroidViewModel(application){

    private val _status = MutableLiveData<FitriteApiStatus>()
    val status: LiveData<FitriteApiStatus>
        get() = _status

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