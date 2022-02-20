package com.example.doctors.main.doctors_list.data

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


    suspend fun updatePlace(placeToWrite: PlaceToWrite) = withContext(dispatcher) {
        return@withContext firestore
            .collection("doctors").document(placeToWrite.idDoctor)
            .collection("years").document(placeToWrite.date.year.toString())
            .collection("months").document(placeToWrite.date.month.toString())
            .collection("days").document(placeToWrite.date.day.toString())
            .collection("places").document(placeToWrite.number.toString())
            .update("idPatient", placeToWrite.idPatient)
    }

    fun enableListenerCollection(doctor: String, currentDate: Date) {

        val tempList = mutableListOf<PlaceToWrite>()

        for (i in 0 until COUNT_PLACES_FOR_WRITE_OF_DAY) {
            tempList.add(PlaceToWrite(
                doctor, "", i,
                getTimeByNumber(i), currentDate, false
            ))
        }

        _places.value = tempList

    val query = firestore
        .collection("doctors").document(doctor)
        .collection("years").document(currentDate.year.toString())
        .collection("months").document(currentDate.month.toString())
        .collection("days").document(currentDate.date.toString())
        .collection("places").orderBy("number")

    snapshotListener = query.addSnapshotListener{ value, error ->
        if (value != null) {
            if (value.size() == COUNT_PLACES_FOR_WRITE_OF_DAY) {
                _places.value = value.toObjects(PlaceToWrite::class.java)
            } else {
                val currentList = value.toObjects(PlaceToWrite::class.java)
                val tempList = _places.value!!

                for (place in currentList) {
                    tempList[place.number] = place
                }

                _places.value = tempList
            }
        }
    }
}


private fun getTimeByNumber(number: Int): String {
    return "${number + 9}:00-${number + 10}:00"
}

fun disableListenerCollectionPlaces() {
    snapshotListener.remove()
}

}
