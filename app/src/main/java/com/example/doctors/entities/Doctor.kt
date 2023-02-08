package com.example.doctors.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Doctor(
    val id: String = "",    
    val avaragePrice: Int = 0,
    val name: String = "",
    val countPeopleForRating: Int = 0,
    val rating: Double = 0.0,
    val urlAvatar: String = ""
) : Parcelable
