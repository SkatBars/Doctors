package com.example.doctors.main.doctors_list.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.doctors.COUNT_PLACES_FOR_WRITE_OF_DAY
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Year
import java.util.*

class DoctorsRecordRemoteDataSource(private val firestore: FirebaseFirestore) {

    private val _places = MutableLiveData<MutableList<PlaceToWrite>>()
    val places: LiveData<MutableList<PlaceToWrite>>
        get() = _places

    private val dispatcher = Dispatchers.IO

    private lateinit var snapshotListener: ListenerRegistration

    suspend fun getQueryDoctors(
        keySort: String,
        reverse: Boolean
    ): Task<QuerySnapshot> = withContext(dispatcher) {
        return@withContext if (reverse) {
            firestore.collection("doctors")
                .orderBy(keySort, Query.Direction.DESCENDING).get()
        } else {
            firestore.collection("doctors").orderBy(keySort, Query.Direction.ASCENDING).get()
        }
    }


    suspend fun createTakenPlace(placeToWrite: PlaceToWrite) = withContext(dispatcher) {
        return@withContext firestore
            .collection("doctors").document(placeToWrite.idDoctor)
            .collection("places").document(placeToWrite.id)
            .set(placeToWrite)
    }

    fun enableListenerCollection(doctor: String, year: Int, month: Int, day: Int) {

        val query = firestore
            .collection("doctors").document(doctor)
            .collection("places").whereEqualTo("year", year)
            .whereEqualTo   ("month", month).whereEqualTo("day", day)



        snapshotListener = query.addSnapshotListener { value, error ->
            updateListPlaces(value, doctor, year, month, day)
        }
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
        snapshotListener.remove()
    }

}
