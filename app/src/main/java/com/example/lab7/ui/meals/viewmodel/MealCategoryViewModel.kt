package com.example.lab7.ui.meals.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lab7.ui.meals.repository.MealsRepository
import com.example.lab7.networking.response.MealsCategoriesResponse

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}
