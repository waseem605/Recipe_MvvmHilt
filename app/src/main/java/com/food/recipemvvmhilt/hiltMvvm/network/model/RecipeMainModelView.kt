package com.food.recipemvvmhilt.hiltMvvm.network.model

data class RecipeMainModelView(
    val _links: Links,
    val count: Int,
    val from: Int,
    val hits: List<Hit>,
    val to: Int
)