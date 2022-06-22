package com.example.doctors.ui.views.main.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.doctors.entities.Toothes
import org.koin.core.component.getScopeId

@Composable
fun ChangeCurrentTooth(toothes: List<Toothes>, indexSelected: MutableState<Int>) {

    Tooth(
        isUpperJaw = false,
        tooth = toothes[indexSelected.value],
        padding = 8.dp,
        isSelected = false,
        size = 48.dp
    )

    ArrowsBtn(
        size = 64.dp,
        tint = Color.Gray,
        padding = PaddingValues(16.dp),
        currentIndex = indexSelected,
        sizeToothes = toothes.size
    )
}

@Composable
private fun ArrowsBtn(
    size: Dp,
    tint: Color,
    padding: PaddingValues,
    currentIndex: MutableState<Int>,
    sizeToothes: Int,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = {
            if (currentIndex.value == 0) {
                currentIndex.value = sizeToothes -1
            }else {
                currentIndex.value = currentIndex.value - 1
            }

        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "left",
                Modifier
                    .size(size = size)
                    .padding(padding),
                tint = tint,
            )
        }

        TextButton(onClick = {
            currentIndex.value = (currentIndex.value + 1) % sizeToothes
        }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "right",
                Modifier
                    .width(size)
                    .height(size)
                    .padding(padding),
                tint = tint
            )
        }

    }
}