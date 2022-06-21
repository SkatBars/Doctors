package com.example.doctors.datebase

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.doctors.entities.Toothes
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

@SuppressLint("StaticFieldLeak")
object UserRemoteDataSource {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getToothes(userId: String): Task<DocumentSnapshot> {
        return firestore.collection("users").document(userId).get()
    }

    suspend fun getHistory(userId: String) {
        firestore.collection("users").document(userId).collection("history").get()
    }
}