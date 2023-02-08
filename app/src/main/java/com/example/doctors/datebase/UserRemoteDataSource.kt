package com.example.doctors.datebase

import android.annotation.SuppressLint
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
object UserRemoteDataSource {
    private val firestore = FirebaseFirestore.getInstance()
    private val dispatcher = Dispatchers.IO

    suspend fun getUserInfo(userId: String): Task<DocumentSnapshot> = withContext(dispatcher) {
        return@withContext firestore.collection("users").document(userId).get()
    }

    suspend fun getHistory(userId: String) = withContext(dispatcher) {
        return@withContext firestore.collection("users").document(userId).collection("history").get()
    }
}