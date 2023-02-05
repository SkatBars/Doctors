package com.example.doctors.datebase

import android.annotation.SuppressLint
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("StaticFieldLeak")
object UserRemoteDataSource {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getUserInfo(userId: String): Task<DocumentSnapshot> {
        return firestore.collection("users").document(userId).get()
    }

    suspend fun getHistory(userId: String) {
        firestore.collection("users").document(userId).collection("history").get()
    }
}