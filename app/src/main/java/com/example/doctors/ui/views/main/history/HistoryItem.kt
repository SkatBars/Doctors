package com.example.doctors.ui.views.main.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doctors.DoctorsScreen
import com.example.doctors.entities.History
import com.example.doctors.ui.components.*
import java.text.SimpleDateFormat

@Composable
fun HistoryItem(history: History, updateRating: (newValue: Int) -> Unit, currentRating: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Title(text = history.nameDoctor, fontSize = 20.sp)

            TextWithCaption(
                caption = "Дата",
                text = SimpleDateFormat("dd.mm.yyyy").format(history.date),
                fontSize = 16.sp
            )

            TextWithCaption(
                caption = "Детали",
                text = history.description,
                fontSize = 16.sp
            )

            RatingView(currentRating = currentRating, onChangeRating = {updateRating(it)})
        }
    }
}