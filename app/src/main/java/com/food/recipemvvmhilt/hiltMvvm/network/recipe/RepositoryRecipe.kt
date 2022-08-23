package com.food.recipemvvmhilt.hiltMvvm.network.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.food.recipemvvmhilt.hiltMvvm.network.RecipeApi
import com.food.recipemvvmhilt.hiltMvvm.network.model.RecipeMainModelView
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RepositoryRecipe() {

    companion object{
        private const val appId = "7327aa6c"
        private const val appKey = "74cc5cf7b60cf0ad918931c30f64f7d4"
        private const val type = "public"
        private const val BASE_URL = "https://api.edamam.com/"

    }


    private val weatherLiveData = MutableLiveData<RecipeMainModelView>()

    val weatherList : LiveData<RecipeMainModelView>
    get() = weatherLiveData


    fun getRecipe(name:String,menuType:String){
        val client = OkHttpClient.Builder()
        client.connectTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(interceptor)
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(RecipeApi::class.java)

        val call= api.getRecipe(name = name, appId, app_key = appKey, type = type, mealType = menuType)

        call.enqueue(object : Callback<RecipeMainModelView> {
            override fun onResponse(
                call: Call<RecipeMainModelView>,
                response: Response<RecipeMainModelView>
            ) {
                if (response.isSuccessful) {
                    weatherLiveData.postValue(response.body())
                    Log.d("454545454","========response.body()!!=========$response.body()!!====")
                }else{
                    Log.d("454545454","========response.body()!!=========${response.body()}====")
                }
            }

            override fun onFailure(call: Call<RecipeMainModelView>, t: Throwable) {
                Log.d("454545454","========response.body()!!=========${t.message}====")
            }
        })
       /* val result = service.getWeatherDetailsMVVM(lat,long,appId)
        if (result !=null){
            weatherLiveData.postValue(result.body())
        }*/
    }
}