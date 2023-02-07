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

    private val _places = MutableLiveData<MutableList<PlaceToWrite>>()
    val places: LiveData<MutableList<PlaceToWrite>>
        get() = _places

    private val _doctors = MutableLiveData<MutableList<Doctor>>()
    val doctors: LiveData<MutableList<Doctor>>
        get() = _doctors

    private val dispatcher = Dispatchers.IO

    private lateinit var snapshotListenerPlaces: ListenerRegistration
    private lateinit var snapshotListenerDoctors: ListenerRegistration

    fun getQueryDoctors(keySort: String, reverse: Boolean ): Query {
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

    fun enableListenerCollectionPlacces(doctor: String, year: Int, month: Int, day: Int) {

        val query = firestore
            .collection("doctors").document(doctor)
            .collection("places").whereEqualTo("year", year)
            .whereEqualTo  ("month", month).whereEqualTo("day", day)

        snapshotListenerPlaces = query.addSnapshotListener { value, error ->
            updateListPlaces(value, doctor, year, month, day)
        }
    }

    suspend fun createTakenPlace(placeToWrite: PlaceToWrite) = withContext(dispatcher) {
        return@withContext firestore
            .collection("doctors").document(placeToWrite.idDoctor)
            .collection("places").document(placeToWrite.id)
            .set(placeToWrite)
    }

    private fun updateListPlaces(
        value: QuerySnapshot?,
        doctor: String,
        year: Int,
        month: Int,
        day: Int
    ) {
        val tempList = mutableListOf<PlaceToWrite>()

        for (i in 0 until COUNT_PLACES_FOR_WRITE_OF_DAY) {
            tempList.add(
                PlaceToWrite(
                    doctor, "", i,
                    getTimeByNumber(i), year,
                    month, day, false
                )
            )
        }

        val currentList = value?.toObjects(PlaceToWrite::class.java)

        if (currentList != null) {
            for (place in currentList) {
                tempList[place.number] = place
            }
        }

        _places.value = tempList
    }

    private fun getTimeByNumber(number: Int): String {
        return if (number <= 3) {
            "${number + 9}:00-${number + 10}:00"
        } else {
            "${number + 10}:00-${number + 11}:00"
        }
    }

    fun disableListenerCollectionPlaces() {
        snapshotListenerPlaces.remove()
    }

    fun disableListenerCollectionDoctors() {
        snapshotListenerDoctors.remove()
    }

}
