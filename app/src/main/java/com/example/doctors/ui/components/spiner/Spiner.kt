package com.example.doctors.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.doctors.R
import com.example.doctors.ui.views.main.doctors.chooseDoctor.KeyForSort

@Composable
fun MySpinner(
    items: List<KeyForSort>,
    hint: String,
    tint: Color,
    padding: PaddingValues,
    onClick: (text: KeyForSort) -> Unit,
) {

    val isExpand = remember { mutableStateOf(false)}

    Box(contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = hint,
                fontWeight = FontWeight.Bold,
                //color = tint
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_sort),
                contentDescription = "to expand",
                Modifier
                    .height(25.dp)
                    .width(25.dp)
                    .clickable { isExpand.value = isExpand.value.not() },
                tint = tint
            )
        }

        DropdownMenu(
            expanded = isExpand.value,
            onDismissRequest = { isExpand.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            for (item in items) {
                DropdownMenuItem(onClick = {
                    onClick(item)
                    isExpand.value = false
                }) {
                    Text(text = item.text)
                }
            }
        }
    }
}