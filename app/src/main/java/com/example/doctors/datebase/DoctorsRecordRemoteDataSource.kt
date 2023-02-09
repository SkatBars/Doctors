package com.example.doctors.datebase

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.doctors.COUNT_PLACES_FOR_WRITE_OF_DAY
import com.example.doctors.entities.Doctor
import com.example.doctors.entities.PlaceToWrite
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DoctorsRecordRemoteDataSource {

    @SuppressLint("StaticFieldLeak")
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _doctors = MutableLiveData<MutableList<Doctor>>()
    val doctors: LiveData<MutableList<Doctor>>
        get() = _doctors

    private val dispatcher = Dispatchers.IO

    private lateinit var snapshotListenerDoctors: ListenerRegistration

    fun getQueryDoctors(keySort: String, reverse: Boolean): Query {
        return if (reverse) {
            firestore.collection("doctors")
                .orderBy(keySort, Query.Direction.DESCENDING)
        } else {
            firestore.collection("doctors").orderBy(keySort, Query.Direction.ASCENDING)
        }
    }

    fun enableListenerCollectionDoctor(keySort: String, reverse: Boolean) {
        val query = getQueryDoctors(keySort = keySort, reverse = reverse)
        snapshotListenerDoctors = query.addSnapshotListener { value, error ->
            _doctors.value = value?.toObjects(Doctor::class.java)
        }
    }

    fun getQueryPlaces(doctor: String, year: Int, month: Int, day: Int): Query {
        return firestore
            .collection("doctors").document(doctor)
            .collection("places").whereEqualTo("year", year)
            .whereEqualTo("month", month).whereEqualTo("day", day)
    }


    suspend fun getDoctorById(idDoctor: String) = withContext(dispatcher) {
         return@withContext firestore.collection("doctors").document(idDoctor).get()
    }

    suspend fun updateRating(doctorId: String, rating: Double, countPeopleForRating: Int) =
        withContext(dispatcher) {
            return@withContext firestore.collection("doctors")
                .document(doctorId).update(
                    "rating", rating,
                    "countPeopleForRating", countPeopleForRating
                )
        }

    suspend fun createTakenPlace(placeToWrite: PlaceToWrite) = withContext(dispatcher) {
        return@withContext firestore
            .collection("doctors").document(placeToWrite.idDoctor)
            .collection("places").document(placeToWrite.id)
            .set(placeToWrite)
    }

    suspend fun deleteTakenPlace(placeId: String, idDoctor: String) = withContext(dispatcher) {
        return@withContext firestore
            .collection("doctors").document(idDoctor)
            .collection("places").document(placeId)
            .delete()
    }
}
