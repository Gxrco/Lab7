package com.example.lab7.ui.mealdetail.repository

import com.example.lab7.networking.MealsWebService
import com.example.lab7.networking.response.MealDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MealDetailRepository(private val webService: MealsWebService = MealsWebService()) {

    suspend fun getMealDetail(mealId: String): MealDetailResponse? {
        println("Attempting to fetch meal detail with mealId: $mealId") // Imprime el mealId
        println("URL: https://www.themealdb.com/api/json/v1/1/lookup.php?i=$mealId") // Imprime la URL completa

        return withContext(Dispatchers.IO) {
            try {
                val response = webService.getMealDetail(mealId)
                println("Response: $response") // Imprime la respuesta completa
                response
            } catch (e: Exception) {
                println("Error: ${e.localizedMessage}") // Imprime cualquier error que ocurra
                null
            }
        }
    }
}