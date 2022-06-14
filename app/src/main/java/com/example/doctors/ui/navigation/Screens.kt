package com.example.doctors

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

const val RC_SIGN_IN = 1
const val COUNT_PLACES_FOR_WRITE_OF_DAY = 8

sealed class Screen (val route: String) {
    object SignIn: Screen("signIn")
    object Registration: Screen("Registration")
    object Main: Screen("main")
}

sealed class MainScreen(
    val route: String,
    @StringRes val labelResourcesId: Int,
    @DrawableRes val iconResourcesId: Int
) {
    object Profile : MainScreen("profile", R.string.profile, R.drawable.ic_home)
    object History : MainScreen("history", R.string.history, R.drawable.ic_history)
    object Doctors : MainScreen("doctor", R.string.choose_doctor, R.drawable.ic_info)
}

sealed class DoctorsScreen(val route: String) {
    object ChooseDoctor: DoctorsScreen("chooseDoctor")
    object PlaceToWrite: DoctorsScreen("placeToWrite")

}

