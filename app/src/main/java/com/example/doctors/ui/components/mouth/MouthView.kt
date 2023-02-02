package com.example.doctors.ui.views.main.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.doctors.R
import com.example.doctors.entities.Toothes
import org.koin.core.definition.indexKey
import kotlin.math.absoluteValue


@Composable
fun Mouth(toothes: List<Toothes>, indexSelected: MutableState<Int>) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Jaw(isUpperJaw = true, toothes = (toothes.subList(0, 16)), indexSelected = indexSelected)
        Jaw(isUpperJaw = false, toothes = (toothes.subList(16, 32)), indexSelected = indexSelected)
    }
}


@Composable
private fun Jaw(
    isUpperJaw: Boolean = false,
    toothes: List<Toothes>,
    indexSelected: MutableState<Int>
) {

    val difference = if (isUpperJaw) 15 * 4
    else 0

    Row {
        for (i in 0..7) {
            Tooth(
                isUpperJaw = isUpperJaw,
                tooth = toothes[i],
                padding = (difference - i * 4).absoluteValue.dp,
                isSelected = currentToothIsSelected(i, isUpperJaw, indexSelected.value),
                size = 24.dp
            )
        }

        for (i in 7 downTo 0) {
            val index = (7 - i) + 8
            Tooth(
                isUpperJaw = isUpperJaw,
                tooth = toothes[index],
                padding = (difference - i * 4).absoluteValue.dp,
                isSelected = currentToothIsSelected(index, isUpperJaw, indexSelected.value),
                size = 24.dp
            )
        }
    }
}

private fun currentToothIsSelected(index: Int, isUpperJaw: Boolean, indexSelected: Int): Boolean {
    val id = if (isUpperJaw.not()) index + 16 else index
    return indexSelected == id
}


@Composable
fun Tooth(isUpperJaw: Boolean, tooth: Toothes, padding: Dp, isSelected: Boolean, size: Dp) {

    val colorId = if (isSelected) R.color.red_500 else R.color.black

    Icon(
        painter = painterResource(id = tooth.icon),
        contentDescription = tooth.description,
        modifier = Modifier
            .padding(top = padding)
            .size(size)
            .rotate(if (isUpperJaw) 180f else 0f),
        tint = colorResource(id = colorId)
    )
}