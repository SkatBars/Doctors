package com.example.doctors.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.doctors.entities.Doctor
import com.example.doctors.entities.PlaceToWrite

@Composable
fun PlaceToWriteView(doctor: Doctor) {
    Text(text = doctor.name)
}
