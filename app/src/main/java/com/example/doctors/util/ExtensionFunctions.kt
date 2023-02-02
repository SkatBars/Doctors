package com.example.doctors.util

fun String.emailIfValid(): Boolean {
    return (this.isEmpty() || this.matches(Regex("\\S*@\\S*[.]\\S*")))
}