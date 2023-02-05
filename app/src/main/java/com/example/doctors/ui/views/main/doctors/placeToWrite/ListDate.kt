package com.example.doctors.ui.views.main.doctors.placeToWrite

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ListDate(listDate: List<Calendar>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxHeight()
    ) {
        for (i in 0..2) {
            Date(listDate[i], i == 1)
        }

    }
}

@SuppressLint("SimpleDateFormat")
@Composable
private fun Date(currentDate: Calendar, inFocus: Boolean) {
    Column {

        val dateFormat = SimpleDateFormat("dd.MM")
        Text(
            text = dateFormat.format(currentDate.time),
            fontSize = if (inFocus) 36.sp else 24.sp
        )

        val weekFormat = SimpleDateFormat("EEEE", Locale("ru", "RU"))


        Text(
            text = weekFormat.format(currentDate.time),
            fontSize = if (inFocus) 16.sp else 12.sp,

            textAlign = TextAlign.Center
        )
    }
}