package com.example.doctors

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

const val RC_SIGN_IN = 1
const val COUNT_PLACES_FOR_WRITE_OF_DAY = 8

sealed class Screen(
    val route: String,
    @StringRes val labelResourcesId: Int,
    @DrawableRes val iconResourcesId: Int
) {
    object Profile : Screen("profile", R.string.profile, R.drawable.ic_home)
    object History : Screen("history", R.string.history, R.drawable.ic_history)
    object ChooseDoctor : Screen("chooseDoctor", R.string.choose_doctor, R.drawable.ic_info)
}

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
