package com.example.doctors.ui.views.doctors.chooseDoctor

sealed class KeyForSort(
    val text: String,
    val property: String,
    val isReverse: Boolean
) {
    object RatingAscending : KeyForSort("По возрастанию рейтинга", "rating", false)
    object RatingDescending : KeyForSort("По убыванию рейтинга", "rating", true)
    object PriceAscending : KeyForSort("По возрастанию цены", "avaragePrice", false)
    object PriceDescending : KeyForSort("По убыванию цены", "avaragePrice", true)
}

val keysForSort = listOf(
    KeyForSort.RatingAscending,
    KeyForSort.RatingDescending,
    KeyForSort.PriceAscending,
    KeyForSort.PriceDescending
)
