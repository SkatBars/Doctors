package com.example.doctors.main.doctorsAppointment.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class DoctorsRecordRemoteDataSource(private val firestore: FirebaseFirestore) {

    private val _places = MutableLiveData<List<PlaceToWrite>>()
    val places: LiveData<List<PlaceToWrite>>
        get() = _places

    private val dispatcher = Dispatchers.IO

    private lateinit var snapshotListener: ListenerRegistration

    val optionsForRecyclerViewDoctors = {
        val query = firestore.collection("doctors")
        FirestoreRecyclerOptions
            .Builder<PlaceToWrite>()
            .setQuery(query, PlaceToWrite::class.java)
            .build()
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
        val query = firestore
            .collection("doctors").document(doctor)
            .collection("years").document(currentDate.year.toString())
            .collection("months").document(currentDate.month.toString())
            .collection("days").document(currentDate.day.toString())
            .collection("places").orderBy("number")

        snapshotListener = query.addSnapshotListener { value, error ->
            _places.value = value!!.toObjects(PlaceToWrite::class.java)
        }
    }

    fun disableListenerCollectionPlaces() {
        snapshotListener.remove()
    }

}
