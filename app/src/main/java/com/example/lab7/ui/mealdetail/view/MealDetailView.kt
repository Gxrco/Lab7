package com.example.lab7.ui.mealdetail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab7.ui.mealdetail.viewmodel.MealDetailViewModel


@Composable
fun MealDetailScreen(mealId: String) {

    val viewModel: MealDetailViewModel = viewModel()

    LaunchedEffect(mealId) {
        viewModel.getMealDetail(mealId)
    }

    val mealDetail by viewModel.mealDetail.collectAsState(null)


    Column(Modifier.fillMaxWidth()) {
        if (mealDetail != null) {
            Column() {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(70.dp)
                    .background(
                        color = Color(0xFF1f80c4),
                        shape = RoundedCornerShape(5.dp))) {
                    Text(text = mealDetail!!.meals?.first()?.strMeal ?: "Cargando...",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .align(CenterVertically)
                            .padding(horizontal = 10.dp)
                    )
                }
                Divider(thickness = 3.dp)
                Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(10.dp)){
                    Text(text = mealDetail!!.meals?.first()?.strInstructions ?: "No instructions available",
                        textAlign = TextAlign.Justify,
                        color = Color.White,
                        modifier = Modifier
                            .background(
                                color = Color(0xFF1d86cf),
                                shape = RoundedCornerShape(5.dp))
                            .padding(15.dp)
                    )
                }

            }

        } else {
            Text("Cargando detalles de la comida...")
        }
    }
}