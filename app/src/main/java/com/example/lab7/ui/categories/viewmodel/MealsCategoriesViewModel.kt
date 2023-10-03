package com.example.lab7.ui.categories.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lab7.networking.response.CategoriesResponse
import com.example.lab7.ui.categories.repository.MealsItemCategories

class MealsCategoriesViewModel(private val repository: MealsItemCategories = MealsItemCategories()): ViewModel() {
    fun getMealsInCategory(categoryId: String, successCallback: (response: CategoriesResponse?) -> Unit) {
        println("ViewModel getMealsInCategory Invoked with categoryId: $categoryId")

        repository.getMealsItemCategories(categoryId) { response ->
            successCallback(response)
        }
    }
}