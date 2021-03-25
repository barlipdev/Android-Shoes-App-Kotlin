package com.barlipdev.fitrite.ui.home.shoe.size.sizechart

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import java.lang.IllegalArgumentException

class SizeChartViewModel(application: Application) : AndroidViewModel(application) {

    val _currentPos = MutableLiveData<Int>()
    val currentPos: LiveData<Int>
        get() = _currentPos

    fun setCurrentSizePosition(currentPos: Int){
        _currentPos.value = currentPos
        Log.i("currentPos",currentPos.toString())
    }

    class Factory(val app: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SizeChartViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return SizeChartViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to create viewmodel")
        }
    }

}