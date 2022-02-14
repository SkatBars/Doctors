package com.example.doctors.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("setPrice")
fun setPrice(view: TextView, price: Int) {
    view.text = "Средний чек: $price руб"
}

