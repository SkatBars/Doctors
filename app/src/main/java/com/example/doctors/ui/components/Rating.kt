package com.example.doctors.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.doctors.R
import com.example.doctors.ui.theme.Gray500
import com.example.doctors.ui.theme.Orange200

@Composable
fun RatingView(currentRating: Int, onChangeRating: (newValue: Int) -> Unit) {
    Row {
        repeat(5) {
            val color = if(currentRating <= it) Gray500 else Orange200

            IconButton(onClick = { onChangeRating(it + 1) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "rating $it",
                    tint = color
                )
            }
        }
    }
}