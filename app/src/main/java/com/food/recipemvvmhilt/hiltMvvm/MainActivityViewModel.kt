package com.food.recipemvvmhilt.hiltMvvm

import androidx.lifecycle.ViewModel
import com.food.recipemvvmhilt.hiltMvvm.network.RetroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RetroRepository):ViewModel() {

    var mWeatherData = repository.mainWeatherData


    fun callData(name:String,mealType:String){
        repository.makeApiCall(name,mealType)
    }

}