package com.example.doctors.entities

import androidx.annotation.DrawableRes
import androidx.compose.runtime.MutableState
import com.example.doctors.R

sealed class Toothes(
    @DrawableRes val icon: Int,
    val description: String,
    val id: String
) {
    object BrokenTooth : Toothes(R.drawable.ic_broken_tooth, "Поврежденный", "broken")
    object HealthyTooth : Toothes(R.drawable.ic_tooth, "Здоровый", "healthy")
    object InWorkTooth : Toothes(R.drawable.ic_tooth_heart, "Лечится", "inWork")
    object СariesTooth : Toothes(R.drawable.ic_caries_tooth, "Кариес", "caries")
}
