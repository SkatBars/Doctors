package com.example.doctors.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.doctors.R

@Composable
fun BackgroundAuthorization(
    sizeBackgroundImage: Dp,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
        Image(
            painter = painterResource(id = R.drawable.ic_doctor),
            contentDescription = "icon_doctor",
            modifier = Modifier
                .height(sizeBackgroundImage)
                .fillMaxWidth()
        )

        BackgroundRoundCard(
            color = Color.White,
            radius = 16.dp
        ) { content() }
    }
}

@Composable
fun BackgroundRoundCard(
    color: Color,
    paddingValues: PaddingValues = PaddingValues(0.dp),
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