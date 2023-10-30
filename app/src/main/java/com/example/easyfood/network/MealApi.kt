package com.example.easyfood.network

import com.example.easyfood.model.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php?")
    fun getMealById(@Query("i") id:String):Call<MealList>
}