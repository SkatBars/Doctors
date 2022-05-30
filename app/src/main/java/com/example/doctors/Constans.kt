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