package com.example.doctors.ui.views.main.doctors.placeToWrite

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
fun ChangeDate(chooseDate: MutableState<Calendar>, viewModel: AppointmentViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = 10.dp
    ) {

        ArrowsBtn(80.dp, Color.Gray, PaddingValues(end = 16.dp), chooseDate)

        ListDate(getListCurrentDate(chooseDate = chooseDate.value))
    }
}

@Composable
private fun ListDate(listDate: List<Calendar>) {


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

@Composable
private fun ArrowsBtn(
    size: Dp,
    tint: Color,
    padding: PaddingValues,
    chooseDate: MutableState<Calendar>
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = { changeDate(chooseDate, -1)}) {
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

        TextButton(onClick = { changeDate(chooseDate, 1) }) {
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

private fun getListCurrentDate(chooseDate: Calendar): List<Calendar> {
    val previousDate = (chooseDate.clone() as Calendar)
    previousDate.add(Calendar.DATE, -1)

    val nextDate = (chooseDate.clone() as Calendar)
    nextDate.add(Calendar.DATE, 1)

    return listOf(previousDate, chooseDate, nextDate)
}

private fun changeDate(currentDate: MutableState<Calendar>, number: Int) {
    val tempDate = Calendar.getInstance()
    tempDate.time = currentDate.value.time
    tempDate.add(Calendar.DATE, number)
    currentDate.value = tempDate
}