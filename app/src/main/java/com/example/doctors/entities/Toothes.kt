package com.example.doctors.entities

import androidx.annotation.DrawableRes
import com.example.doctors.R

sealed class Toothes(
    @DrawableRes val icon: Int,
    val description: String,
    val id: String
) {
    object BrokenTooth : Toothes(R.drawable.ic_broken_tooth, "Зуб поврежден", "broken")
    object HealthyTooth : Toothes(R.drawable.ic_tooth, "Зуб здоров", "healthy")
    object InWorkTooth : Toothes(R.drawable.ic_tooth_heart, "В процессе лечения", "inWork")
    object СariesTooth : Toothes(R.drawable.ic_caries_tooth, "Обнаружен кариес", "caries")
}
