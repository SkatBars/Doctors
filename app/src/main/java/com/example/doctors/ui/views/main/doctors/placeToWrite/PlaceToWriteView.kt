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

        LaunchedEffect(key1 = Unit, block = {
            viewModel.enableListenerCollection(currentDate.value, doctorId = doctor.id)
        })

        LaunchedEffect(key1 = currentDate, block = {
            viewModel.updateDateForPlaces(currentDate = currentDate, viewModel, doctor.id)
        })

        ChangeDate(currentDate)

        val places =
            viewModel.places.observeAsState(listOf()) as MutableState<List<PlaceToWrite>>
        ListPlaces(places = places, viewModel = viewModel)
    }
}

@Composable
private fun ListPlaces(places: MutableState<List<PlaceToWrite>>, viewModel: AppointmentViewModel) {
    LazyColumn(Modifier.padding(top = 8.dp)) {
        items(places.value) { place ->
            PlaceItem(
                place = place,
                takePlace = { place, patientId -> viewModel.takePlace(place, patientId) },
                takeOfPlace = { placeId, doctorId ->
                    viewModel.takeOfPlace(placeId, doctorId)
                }
            )
        }
    }
}
