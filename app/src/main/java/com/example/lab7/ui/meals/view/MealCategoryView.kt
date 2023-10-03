package com.example.lab7.ui.meals.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import com.example.lab7.networking.response.MealResponse
import com.example.lab7.ui.meals.viewmodel.MealsCategoriesViewModel

@Composable
fun MealsCategoriesScreen(navController: NavController) {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val rememberedMeals: MutableState<List<MealResponse>> = remember { mutableStateOf(emptyList<MealResponse>()) }

    viewModel.getMeals { response ->
        val mealsFromTheApi = response?.categories
        rememberedMeals.value = mealsFromTheApi.orEmpty()
    }

    LazyColumn {
        items(rememberedMeals.value) { meal ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .background(color = Color(0xFF1d86cf), shape = RoundedCornerShape(10.dp))){
                AsyncImage(
                    model = meal.imageUrl,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    error = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = meal.name,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier.height(70.dp)
                )
                ClickableText(
                    text = AnnotatedString(text = meal.name),
                    onClick = { offset ->
                        navController.navigate("Category/${meal.name}")
                    },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(vertical = 10.dp)
                        .align(CenterVertically),
                    style = TextStyle(fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}
