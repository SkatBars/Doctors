package com.example.doctors.main.doctors_list.data

import java.util.*

data class PlaceToWrite(
    val idDoctor: String = "",
    var idPatient: String = "",
    val number: Int = 0,
    val time: String = "",
    val year: Int = 2022,
    val month: Int = 2,
    val day: Int = 20,
    @field:JvmField
    var isTaken: Boolean = false,
    val id: String = UUID.randomUUID().toString()
)
