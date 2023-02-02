package com.example.doctors.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val toothes: List<String> = listOf(),
    val phoneNumber: String = "",
    val information: String,
    val name: String = "",
    val urlAvatar: String = ""
) : Parcelable

