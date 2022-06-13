package com.example.doctors.ui.views.doctors.placeToWrite

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doctors.R
import com.example.doctors.entities.PlaceToWrite
import com.example.doctors.view_model.AppointmentViewModel

@Composable
fun PlaceItem(place: PlaceToWrite, viewModel: AppointmentViewModel) {

    Card(elevation = 15.dp, modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {

        Row(modifier = Modifier.height(70.dp)) {
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(10.dp),
                color = colorResource(id = getColorIdByIsTaken(place.isTaken)),
            )

            Column {
                Text(text = place.time, fontSize = 24.sp, modifier = Modifier.padding(start = 8.dp))
                TextButton(
                    onClick = { /*TODO*/ },
                    enabled = place.isTaken.not()
                ) {
                    Text(text = "Записаться")
                }

            }
        }


    }


}

private fun getColorIdByIsTaken(isTaken: Boolean): Int {
    return if (isTaken) {
        R.color.orange_200
    } else {
        R.color.blue_200
    }
}