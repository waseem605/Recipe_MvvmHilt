package com.food.recipemvvmhilt.hiltMvvm.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.food.recipemvvmhilt.hiltMvvm.network.recipe.RecipeViewModel
import com.food.recipemvvmhilt.hiltMvvm.network.recipe.RepositoryRecipe

class ViewModelFactory(
    private val repositoryWeather: RepositoryRecipe
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeViewModel(repositoryWeather) as T
    }
}