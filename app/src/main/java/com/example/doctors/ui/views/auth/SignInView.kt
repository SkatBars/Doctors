package com.example.doctors.ui.views.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun SignInView() {
    Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
        Text(text = "Skatik", color = Color.White, fontSize = 32.sp)
        BackgroundRoundCard(
            color = Color.White, paddingValues = PaddingValues(top = 48.dp), radius = 16.dp) {

        }
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

