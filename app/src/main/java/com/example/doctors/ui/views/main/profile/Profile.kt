package com.example.doctors.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doctors.R
import com.example.doctors.entities.Toothes
import com.example.doctors.view_model.InformationUserViewModel
import kotlin.math.absoluteValue

@Composable
fun Profile() {
    val viewModelUser: InformationUserViewModel = viewModel()

    viewModelUser.getToothes()

    val toothes: List<Toothes> by viewModelUser.toothes.observeAsState(emptyList())
    if (toothes.isNotEmpty()) {
        Column {
            Jaw(rotateDegrees = 180f, toothes = toothes.subList(0, 15))
            Jaw(toothes = toothes.subList(16, 32))
        }
    }

}

@Composable
private fun Jaw(rotateDegrees: Float = 0f, toothes : List<Toothes>) {

    val difference = if (rotateDegrees == 0f) 0
    else 15*4

    Row(modifier = Modifier.padding(top = 16.dp)) {
        for (i in 0..7) {
            Tooth(rotateDegrees = rotateDegrees, tooth = toothes[i], padding = (difference - i*4).absoluteValue.dp)
        }

        for (i in 7 downTo 0) {
            Tooth(rotateDegrees = rotateDegrees, tooth = toothes[i*2], padding = (difference - i*4).absoluteValue.dp)
        }
    }
}

@Composable
private fun Tooth(rotateDegrees: Float, tooth: Toothes, padding: Dp) {
    Icon(
        painter = painterResource(id = tooth.icon),
        contentDescription = tooth.description,
        modifier = Modifier
            .padding(top = padding)
            .size(25.dp)
            .rotate(rotateDegrees),
        tint = colorResource(id = R.color.black)
    )
}