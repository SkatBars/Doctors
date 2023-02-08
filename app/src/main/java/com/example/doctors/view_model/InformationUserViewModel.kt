package com.example.doctors.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors.datebase.FirebaseAuthDataSource
import com.example.doctors.datebase.UserRemoteDataSource
import com.example.doctors.entities.History
import com.example.doctors.entities.User
import kotlinx.coroutines.launch

class InformationUserViewModel : ViewModel() {
    val authDb = FirebaseAuthDataSource
    val userDb = UserRemoteDataSource

    private val _userInfo = MutableLiveData<User?>(null)
    val userInfo: LiveData<User?>
        get() = _userInfo

    private val _history = MutableLiveData<List<History>>(emptyList())
    val history: LiveData<List<History>>
        get() = _history

    fun getUserInformation() = viewModelScope.launch {
        val userId = authDb.getUser()?.uid
        userId!!.let {
            userDb.getUserInfo(userId = it)
                .addOnSuccessListener { docScnapshot ->
                    val user = docScnapshot.toObject(User::class.java)
                    _userInfo.value = user
                }
        }
    }

    fun getHistory() = viewModelScope.launch {
        val userId = authDb.getUser()?.uid
        userDb.getHistory(userId!!).addOnSuccessListener {
            _history.value = it.toObjects(History::class.java)
        }
    }

    fun updateRating(history: History, newRating: Int) = viewModelScope.launch {
        val userId = authDb.getUser()?.uid
        userDb.updateRatingInHistory(historyId = history.id, userId = userId!!, newRating)
            .addOnSuccessListener {
            }
    }
}