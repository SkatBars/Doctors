package com.example.doctors.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class History(
    val id: String = UUID.randomUUID().toString(),
    val date: Date = Date(),
    val nameDoctor: String = "",
    val doctorId: String = "",
    val description: String = "",
    val ratingForDoctor: Int = 0,
) : Parcelable
