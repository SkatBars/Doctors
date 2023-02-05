package com.example.doctors.util

import com.example.doctors.entities.Toothes

fun parseListIdToListToothes(listId: List<String>): MutableList<Toothes> {
    val toothes = mutableListOf<Toothes>()
    listId.forEach { toothes.add(getToothById(it)) }
    return toothes
}

private fun getToothById(toothId: String): Toothes {
    return when(toothId) {
        Toothes.BrokenTooth.id -> Toothes.BrokenTooth
        Toothes.InWorkTooth.id -> Toothes.InWorkTooth
        Toothes.HealthyTooth.id -> Toothes.HealthyTooth
        else -> Toothes.СariesTooth
    }
}