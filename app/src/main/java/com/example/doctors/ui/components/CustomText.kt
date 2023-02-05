package com.example.doctors.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doctors.R

@Composable
fun TextWithCaption(
    caption: String,
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 24.sp,
) {
    Row(horizontalArrangement = Arrangement.Start) {
        Text(
            text = caption,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = text,
            fontSize = fontSize,
            modifier = modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun RatingText(rating: Double) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$rating",
            modifier = Modifier.padding(top = 8.dp),
            color = Color.Gray,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
        )

        Icon(
            painter = painterResource(
                id = R.drawable.ic_star
            ),
            contentDescription = "rating",
            modifier = Modifier.padding(top = 8.dp, start = 4.dp),
            tint = Color.Gray
        )
    }
}

@Composable
fun Title(text: String, fontSize: TextUnit) {
    Text(
        text = text,
        color = MaterialTheme.colors.primaryVariant,
        fontWeight = FontWeight.Bold,
        fontSize = fontSize,
    )
}