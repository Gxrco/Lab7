package com.example.lab7.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab7.ui.meals.view.MealsCategoriesScreen
import com.example.lab7.ui.categories.view.MealsItemCategoriesView
import com.example.lab7.ui.mealdetail.view.MealDetailScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NavigationState.Home.route,
        modifier = modifier) {
        composable(route = NavigationState.Home.route) {
            MealsCategoriesScreen(navController)
        }
        composable(route = NavigationState.Category.route) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            if (categoryId != null) {
                // Navigate to the appropriate screen
                MealsItemCategoriesView(categoryId, navController)
            }
        }
        composable(route = "${NavigationState.Detail.route}/{mealId}") { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId")
            if (mealId != null) {
                MealDetailScreen(mealId)
            }
        }
    }
}