package com.food.recipemvvmhilt.hiltMvvm.network.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.food.recipemvvmhilt.hiltMvvm.network.model.RecipeMainModelView

class RecipeViewModel(
    private val repositoryWeather: RepositoryRecipe
):ViewModel() {

    fun getRecipeInModel(name: String, menuType: String){
        repositoryWeather.getRecipe(name,menuType)
    }

    val recipe:LiveData<RecipeMainModelView>
    get() = repositoryWeather.weatherList

}