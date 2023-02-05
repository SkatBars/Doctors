package com.example.doctors.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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