package com.example.doctors.entities

import java.util.*

data class History(
    val id: String = UUID.randomUUID().toString(),
    val date: Date = Date(),
    val doctorId: String = "",
    val description: String = "",
    val ratingForDoctor: Int = 0,
)
