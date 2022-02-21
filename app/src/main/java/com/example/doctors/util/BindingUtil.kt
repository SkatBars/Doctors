package com.example.doctors.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.bumptech.glide.Glide
import com.github.siyamed.shapeimageview.RoundedImageView
import java.lang.String.format
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("loadImage")
fun loadImage(view: RoundedImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("setPrice")
fun setPrice(view: TextView, price: Int) {
    view.text = "Средний чек: $price руб"
}

@SuppressLint("SimpleDateFormat")
@BindingConversion
fun convertDateToString(date: Date): String {
    val formatter = SimpleDateFormat("Дата: dd.MM.yyyy")
    return formatter.format(date)
}

