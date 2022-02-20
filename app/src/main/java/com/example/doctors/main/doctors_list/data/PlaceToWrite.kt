package com.example.doctors.main.doctors_list.data

import java.util.*

data class PlaceToWrite(
    val idDoctor: String = "",
    val idPatient: String = "",
    val number: Int = 0,
    val time: String = "",
    val date: Date = Date(),
    @field:JvmField
    val isTaken: Boolean = false
)
