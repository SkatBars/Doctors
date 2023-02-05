package com.example.doctors.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CircleImage(uriImage: String) {
    GlideImage(
        imageModel = uriImage,

        modifier = Modifier
            .padding(start = 8.dp)
            .size(80.dp)
            .clip(shape = CircleShape)
    )
}
