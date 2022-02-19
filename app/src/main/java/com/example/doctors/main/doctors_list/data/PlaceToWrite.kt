package com.example.doctors.main.doctors_list.data

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
