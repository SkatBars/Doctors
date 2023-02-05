package com.example.doctors.ui.views.main.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.doctors.entities.Toothes

@Composable
fun ChangeCurrentTooth(toothes: List<Toothes>, indexSelected: MutableState<Int>) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ArrowBtn(icon = Icons.Filled.KeyboardArrowLeft) {
            if (indexSelected.value == 0) {
                indexSelected.value = toothes.size - 1
            } else {
                indexSelected.value = indexSelected.value - 1
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Tooth(
                isUpperJaw = false,
                tooth = toothes[indexSelected.value],
                padding = 8.dp,
                isSelected = false,
                size = 64.dp
            )
            Text(toothes[indexSelected.value].description)
        }

        ArrowBtn(icon = Icons.Filled.KeyboardArrowRight) {
            indexSelected.value = (indexSelected.value + 1) % toothes.size
        }
    }

}

@Composable
private fun ArrowBtn(
    icon: ImageVector,
    onClick: () -> Unit
) {

    TextButton(onClick = { onClick() }) {
        Icon(
            imageVector = icon,
            contentDescription = "left",
            Modifier
                .size(size = 128.dp),
            tint = Color.Gray,
        )
    }

}