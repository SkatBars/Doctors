package com.example.doctors.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doctors.entities.Toothes
import com.example.doctors.ui.views.main.profile.ChangeCurrentTooth
import com.example.doctors.ui.views.main.profile.Mouth
import com.example.doctors.view_model.InformationUserViewModel

@Composable
fun Profile() {
    val viewModelUser: InformationUserViewModel = viewModel()

    viewModelUser.getToothes()

    val toothes: List<Toothes> by viewModelUser.toothes.observeAsState(emptyList())
    val indexSelected = remember { mutableStateOf(5) }

    if (toothes.isNotEmpty()) {
        Column {
            Mouth(toothes = toothes, indexSelected = indexSelected)
            ChangeCurrentTooth(toothes = toothes, indexSelected = indexSelected)
        }
    }
}

