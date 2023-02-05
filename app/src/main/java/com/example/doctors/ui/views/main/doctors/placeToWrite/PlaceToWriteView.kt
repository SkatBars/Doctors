package com.example.doctors.ui.views.main.doctors.placeToWrite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doctors.DoctorsScreen
import com.example.doctors.R
import com.example.doctors.entities.Doctor
import com.example.doctors.entities.PlaceToWrite
import com.example.doctors.view_model.AppointmentViewModel
import java.util.*

@Composable
fun PlaceToWriteView(doctor: Doctor, navController: NavController) {
    val viewModel: AppointmentViewModel = viewModel()
    Column {
        val currentDate = remember { mutableStateOf(Calendar.getInstance()) }
        viewModel.enableListenerCollection(currentDate.value, doctorId = doctor.id)

        updateDateForPlaces(currentDate = currentDate, viewModel, doctor.id)
        ChangeDate(currentDate)

        val places =
            viewModel.places.observeAsState(listOf()) as MutableState<List<PlaceToWrite>>
        ListPlaces(places = places, viewModel = viewModel)
    }
}


fun updateDateForPlaces(
    currentDate: MutableState<Calendar>,
    appointmentViewModel: AppointmentViewModel,
    doctorId: String
) {
    appointmentViewModel.disableListenerCollectionPlaces()
    appointmentViewModel.enableListenerCollection(currentDate.value, doctorId = doctorId)
}

@Composable
private fun TopAppBarPlaceToWrite(doctorName: String, backAction: () -> Unit) {
    TopAppBar {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Button(onClick = { backAction }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back),
                    contentDescription = "back",
                    Modifier
                        .width(30.dp)
                        .height(30.dp)
                )
            }

            Text(text = doctorName, fontSize = 24.sp)
        }
    }
}

@Composable
private fun ListPlaces(places: MutableState<List<PlaceToWrite>>, viewModel: AppointmentViewModel) {
    LazyColumn(Modifier.padding(top = 8.dp)) {
        items(places.value) { place ->
            PlaceItem(place = place) { viewModel.takePlace(it) }
        }
    }
}
