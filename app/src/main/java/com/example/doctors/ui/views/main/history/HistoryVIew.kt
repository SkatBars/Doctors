package com.example.doctors.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doctors.DoctorsScreen
import com.example.doctors.entities.Doctor
import com.example.doctors.entities.History
import com.example.doctors.ui.components.CircleImage
import com.example.doctors.ui.components.RatingText
import com.example.doctors.ui.components.TextWithCaption
import com.example.doctors.ui.components.Title
import com.example.doctors.ui.views.main.history.HistoryItem
import com.example.doctors.view_model.DoctorsListViewModel
import com.example.doctors.view_model.InformationUserViewModel
import kotlin.math.absoluteValue

@Composable
fun HistoryView() {
    val userViewModel = viewModel(InformationUserViewModel::class.java)
    val doctorViewModel = viewModel(DoctorsListViewModel::class.java)

    val history = userViewModel.history.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        userViewModel.getHistory()
    })

    if (history.value?.isNotEmpty() == true) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(history.value!!) { historyItem ->
                var currentRating by remember { mutableStateOf(historyItem.ratingForDoctor) }

                HistoryItem(
                    history = historyItem,
                    updateRating = {
                        val previousRating = currentRating

                        currentRating = it
                        userViewModel.updateRating(historyItem, currentRating)
                        doctorViewModel.updateRating(
                            historyItem.doctorId,
                            currentRating - previousRating,
                            previousRating == 0
                        )
                    },
                    currentRating = currentRating
                )
            }
        }
    }
}

