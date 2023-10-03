package com.example.lab7.ui.categories.repository

import com.example.lab7.networking.MealsWebService

import com.example.lab7.networking.response.CategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsItemCategories(private val webService: MealsWebService = MealsWebService()) {
    fun getMealsItemCategories(categoryId: String, successCallback: (response: CategoriesResponse?) -> Unit) {
        println("Repository getMealsInCategory Invoked with categoryId: $categoryId")

        return webService.getMealsInCategory(categoryId).enqueue(object : Callback<CategoriesResponse> {
            override fun onResponse(call: Call<CategoriesResponse>, response: Response<CategoriesResponse>) {
                println("API Call successful. Response: ${response.body()}, Error: ${response.errorBody()}")

                if (response.isSuccessful)
                    successCallback(response.body())
            }

            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                println("API Call failed. Error: $t")
            }
        })
    }
}