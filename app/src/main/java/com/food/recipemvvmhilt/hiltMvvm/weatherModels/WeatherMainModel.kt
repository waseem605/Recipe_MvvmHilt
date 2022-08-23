package com.food.recipemvvmhilt.hiltMvvm.weatherModels

data class WeatherMainModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherList>,
    val message: Int
)