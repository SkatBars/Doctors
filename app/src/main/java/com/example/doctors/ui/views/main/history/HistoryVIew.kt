package com.example.doctors.ui

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.sp
import com.example.doctors.entities.Doctor

@Composable
fun HistoryView() {
    Log.i("QWE", "history")
    Text(text = "History", fontSize = 48.sp)
}