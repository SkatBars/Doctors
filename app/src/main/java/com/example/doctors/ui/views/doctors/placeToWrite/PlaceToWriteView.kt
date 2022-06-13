package com.example.doctors.ui.views.doctors.placeToWrite

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doctors.DoctorsScreen
import com.example.doctors.R
import com.example.doctors.entities.Doctor
import com.example.doctors.entities.PlaceToWrite
import com.example.doctors.view_model.AppointmentViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PlaceToWriteView(doctor: Doctor, navController: NavController) {
    val viewModel: AppointmentViewModel = viewModel()

    Scaffold(topBar = {
        TopAppBarPlaceToWrite(doctorName = doctor.name, backAction = {
            navController.navigate(DoctorsScreen.ChooseDoctor.route)
        })
    }){
        ChangeDate(Calendar.getInstance(), viewModel = viewModel)
    }
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
private fun ListPlaces(places: List<PlaceToWrite>, viewModel: AppointmentViewModel) {
    LazyColumn {

        }
    }
