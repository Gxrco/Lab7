package com.example.lab7.navigation

sealed class NavigationState(val route: String) {
    object Home: NavigationState("Home")
    object Category: NavigationState("Category/{categoryId}")
    object Detail: NavigationState("MealDetail/{mealId}")

}