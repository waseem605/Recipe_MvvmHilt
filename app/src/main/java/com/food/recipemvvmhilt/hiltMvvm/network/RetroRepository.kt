package com.food.recipemvvmhilt.hiltMvvm.network

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.food.recipemvvmhilt.hiltMvvm.network.model.RecipeMainModelView
import com.food.recipemvvmhilt.hiltMvvm.weatherModels.WeatherMainModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val retroServiceInstance: RetroServiceInstance) {
    val mainWeatherData = MutableLiveData<RecipeMainModelView>()
    companion object{
        private const val appId = "7327aa6c"
        private const val appKey = "74cc5cf7b60cf0ad918931c30f64f7d4"
//        private const val appId = "7327aa6c"
//        private const val appKey = "74cc5cf7b60cf0ad918931c30f64f7d4"
        private const val type = "public"
        private const val BASE_URL = "https://api.edamam.com/"
        private const val TAG = "RetroRepository"


    }
    fun makeApiCall(name:String,mealType:String){
        val call:Call<RecipeMainModelView> = retroServiceInstance.getRecipe(name,appId, appKey, type,mealType)

        call.enqueue(object :Callback<RecipeMainModelView>{
            override fun onResponse(call: Call<RecipeMainModelView>, response: Response<RecipeMainModelView>) {
                Log.d(TAG, "onResponse: *-*-*--*-*-*-    ${response.message()}")
                Log.d(TAG, "onResponse: *-*-*--*-*-*-    ${response.code()}")
                if (response.isSuccessful) {
                    Log.d(TAG, ":    ========  ${response.message()}")
                    mainWeatherData.value = response.body()
                }
            }

            override fun onFailure(call: Call<RecipeMainModelView>, t: Throwable) {
                Log.d(TAG, "onFailure:    ========  ${t.localizedMessage}")
            }

        })
    }
}