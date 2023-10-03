package com.example.lab7.networking

import com.example.lab7.networking.response.CategoriesResponse
import com.example.lab7.networking.response.MealDetailResponse
import com.example.lab7.networking.response.MealsCategoriesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory

class MealsWebService {

    private lateinit var api: MealsApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    fun getMeals(): Call<MealsCategoriesResponse> {
        return api.getMeals()
    }

    fun getMealsInCategory(categoryId: String): Call<CategoriesResponse> {
        val url = "$categoryId"
        println("WebService getMealsInCategory Invoked with URL: $url")
        return api.getMealsInCategory("$categoryId")
    }

    suspend fun getMealDetail(mealId: String): MealDetailResponse? {
        return api.getMealDetail(mealId)
    }
}