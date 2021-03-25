package com.barlipdev.fitrite.ui.home.shoe.size

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.barlipdev.fitrite.database.getDatabase
import com.barlipdev.fitrite.domain.Shoe
import com.barlipdev.fitrite.repository.FitriteRepository

class ShoeSizeSelectorViewModel(application: Application, shoe: Shoe) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val fitriteRepository = FitriteRepository(database)

    val currentShoe = shoe

    class Factory(val app: Application, val shoe: Shoe): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ShoeSizeSelectorViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return ShoeSizeSelectorViewModel(app,shoe) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }

}