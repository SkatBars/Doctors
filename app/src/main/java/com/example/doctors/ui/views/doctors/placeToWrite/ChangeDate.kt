package com.example.doctors.ui.views.doctors.placeToWrite

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doctors.view_model.AppointmentViewModel
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ChangeDate(chooseDate: Calendar, viewModel: AppointmentViewModel) {
    val listDate = remember { mutableStateOf<List<Calendar>>(emptyList())}

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = 10.dp
    ) {
        updateListCurrentDate(listDate = listDate,chooseDate = chooseDate)
        ArrowsBtn(80.dp, Color.Gray, PaddingValues(end = 16.dp), listDate, chooseDate)
        ListDate(listDate = listDate)
    }
}

@Composable
private fun ListDate(listDate: MutableState<List<Calendar>>) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxHeight()
    ) {
        for (i in 0..2) {
            Date(listDate.value[i], i == 1)
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

@Composable
private fun ArrowsBtn(
    size: Dp,
    tint: Color,
    padding: PaddingValues,
    listDate: MutableState<List<Calendar>>,
    chooseDate: Calendar
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = {
            chooseDate.add(Calendar.DATE, -1)
            updateListCurrentDate(listDate = listDate, chooseDate = chooseDate)
        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "left",
                Modifier
                    .width(size)
                    .height(size)
                    .padding(padding),
                tint = tint,
            )
        }

        TextButton(onClick = {
            chooseDate.add(Calendar.DATE, 1)
            updateListCurrentDate(listDate = listDate, chooseDate = chooseDate)
        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "right",
                Modifier
                    .width(size)
                    .height(size)
                    .padding(padding),
                tint = tint
            )
        }

    }
}

private fun updateListCurrentDate(listDate: MutableState<List<Calendar>>,chooseDate: Calendar) {
    val previousDate = (chooseDate.clone() as Calendar)
    previousDate.add(Calendar.DATE, -1)

    val nextDate = (chooseDate.clone() as Calendar)
    nextDate.add(Calendar.DATE, 1)

    listDate.value = listOf(previousDate, chooseDate, nextDate)
}