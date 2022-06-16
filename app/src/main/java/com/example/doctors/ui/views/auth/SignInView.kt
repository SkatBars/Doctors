package com.example.doctors.ui.views.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doctors.Screen

@Composable
fun SignInView(navController: NavController) {
    Column(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
        Text(text = "Skatik", color = Color.White, fontSize = 32.sp)

    }
}

@Composable
fun BackgroundRoundCard(
    color: Color,
    paddingValues: PaddingValues,
    radius: Dp,
    content: @Composable () -> Unit
    ) {
    Card(
        backgroundColor = color,
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxHeight()
            .fillMaxWidth(),
        shape = RoundedCornerShape(radius),
        content = content
    )
}

