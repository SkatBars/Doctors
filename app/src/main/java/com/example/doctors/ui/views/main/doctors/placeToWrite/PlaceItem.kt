package com.example.doctors.ui.views.main.doctors.placeToWrite

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doctors.R
import com.example.doctors.entities.PlaceToWrite
import com.example.doctors.view_model.AuthorizationViewModel
import org.koin.core.component.getScopeId

@Composable
fun PlaceItem(place: PlaceToWrite, onClick: (place: PlaceToWrite, idPatient: String) -> Unit) {
    val authViewModel = viewModel(AuthorizationViewModel::class.java)

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
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TextButton(
                        onClick = { onClick(place, authViewModel.getIdUser()!!) },
                        enabled = place.isTaken.not()
                    ) {
                        Text(text = "Записаться")
                    }

                    if (authViewModel.getIdUser()!! == place.idPatient) {
                        TextButton(
                            onClick = {  },
                        ) {
                            Text(text = "Снять бронь")
                        }
                    }
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