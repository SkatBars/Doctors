package com.example.doctors.main.doctorsAppointment.data

import java.util.*

data class PlaceToWrite(
    val idDoctor: String,
    val idPatient: String,
    val number: Int,
    val time: String,
    val date: Date,
    @field:JvmField
    val isSuccess: Boolean
)