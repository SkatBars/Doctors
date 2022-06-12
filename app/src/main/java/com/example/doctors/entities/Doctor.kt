package com.example.doctors.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Doctor(
    val id: String = "",    
    val avaragePrice: Int = 0,
    val name: String = "",
    val rating: Double = 0.0,
    val urlAvatar: String = ""
) : Parcelable
