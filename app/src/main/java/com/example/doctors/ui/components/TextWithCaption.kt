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
import androidx.compose.ui.unit.dp

@Composable
fun TextWithCaption(caption: String, text: String, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.Start) {
        Text(text = caption, style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
        Text(
            text = text,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Normal,
            modifier = modifier.padding(start = 8.dp)
        )
    }
}