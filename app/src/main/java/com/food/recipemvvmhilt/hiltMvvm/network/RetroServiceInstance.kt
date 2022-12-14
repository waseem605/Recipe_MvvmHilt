package com.food.recipemvvmhilt.hiltMvvm.network

import com.food.recipemvvmhilt.hiltMvvm.network.model.RecipeMainModelView
import com.food.recipemvvmhilt.hiltMvvm.weatherModels.WeatherMainModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInstance {
    @GET("api/recipes/v2/")
    fun getRecipe(
        @Query("q")name: String,
        @Query("app_id")app_id: String,
        @Query("app_key")app_key: String,
        @Query("type")type: String,
        @Query("mealType")mealType: String): Call<RecipeMainModelView>
}