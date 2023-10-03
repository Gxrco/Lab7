package com.example.lab7.ui.categories.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import com.example.lab7.navigation.NavigationState
import com.example.lab7.networking.response.CategoryResponse
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.lab7.R
import com.example.lab7.ui.categories.viewmodel.MealsCategoriesViewModel


@Composable
fun MealsItemCategoriesView(categoryId: String, navController: NavController) {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val rememberedMeals: MutableState<List<CategoryResponse>> = remember { mutableStateOf(emptyList<CategoryResponse>()) }
    println("Meals Invoked with categoryId: $categoryId")

    viewModel.getMealsInCategory(categoryId) { response ->
        println("Response in Screen: $response")

        if (response != null) {
            rememberedMeals.value = response.categories.orEmpty()
            println("Updated rememberedMeals: ${rememberedMeals.value}")

        }
    }

    LazyColumn {
        items(rememberedMeals.value) { meal ->
            Row(modifier = Modifier.fillMaxWidth().padding(6.dp).background(color = Color(0xFF1d86cf), shape = RoundedCornerShape(10.dp))){
                AsyncImage(
                    model = meal.mealthumb,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    error = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = meal.name,
                    modifier = Modifier.height(70.dp),
                    contentScale = ContentScale.FillHeight
                )
                ClickableText(
                    text = AnnotatedString(text = meal.name),
                    onClick = { offset ->
                        navController.navigate("${NavigationState.Detail.route}/${meal.idmeal}")
                    },
                    modifier = Modifier.padding(horizontal = 20.dp).padding(vertical = 10.dp).align(
                        Alignment.CenterVertically
                    ),
                    style = TextStyle(fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold),
                    maxLines = 1
                )
            }
        }
    }
}

