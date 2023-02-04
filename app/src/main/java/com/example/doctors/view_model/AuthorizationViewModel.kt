package com.example.doctors.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors.datebase.FirebaseAuthDataSource
import com.example.doctors.entities.User
import kotlinx.coroutines.launch

class AuthorizationViewModel() : ViewModel() {

    private val db = FirebaseAuthDataSource

    private val _answerRequestFromDB = MutableLiveData<Result<String>?>()
    val answerRequestSignIn: LiveData<Result<String>?>
        get() = _answerRequestFromDB


    fun isAuthorization() = db.getUser() != null

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            db.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    _answerRequestFromDB.value = Result.success("OK")
                }
                .addOnCanceledListener {
                    _answerRequestFromDB.value =
                        Result.failure(Exception())
                }
                .addOnFailureListener {
                    _answerRequestFromDB.value =
                        Result.failure(Exception())
                }
        }
    }


    fun signOut() = viewModelScope.launch {
        db.signOut()
    }

    fun register(user: User, password: String) {
        viewModelScope.launch {
            db.createUser(user.email, password)
                .addOnSuccessListener {
                    user.id = it.user!!.uid
                    addUserInDb(user)
                }
                .addOnCanceledListener {
                    _answerRequestFromDB.value = Result.failure(Exception())
                }
                .addOnFailureListener {
                    _answerRequestFromDB.value = Result.failure(Exception())
                }
        }
    }

    fun addUserInDb(user: User) = viewModelScope.launch {
        db.addUserInDb(user)
            .addOnSuccessListener {
                _answerRequestFromDB.value = Result.success("Ok")
            }

            .addOnFailureListener {
                _answerRequestFromDB.value = Result.failure(it)
            }
    }
}