package com.example.doctors.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors.datebase.FirebaseAuthDataSource
import kotlinx.coroutines.launch

class AuthorizationViewModel() : ViewModel() {

    private val db = FirebaseAuthDataSource

    private val _openMainFragmentEvent = MutableLiveData<Boolean>(false)
    val openMainFragmentEvent: LiveData<Boolean>
        get() = _openMainFragmentEvent

    private val _answerRequestFromDB = MutableLiveData<Result<String>?>()
    val answerRequestSignIn: LiveData<Result<String>?>
        get() = _answerRequestFromDB


    val gsoDb = db.gso

    init {
        autoAuthorization()
    }

    private fun autoAuthorization() {
        val user = db.getUser()
        if (user != null) {
            _openMainFragmentEvent.value = true
        }
    }

    fun isAuthorization() = db.getUser() != null

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            db.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                with(task) {
                    addOnSuccessListener {
                        _answerRequestFromDB.value = Result.success("OK")
                    }
                    addOnCanceledListener {
                        _answerRequestFromDB.value =
                            Result.failure(Exception())
                    }
                    addOnFailureListener {
                        _answerRequestFromDB.value =
                            Result.failure(Exception())
                    }
                }
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            db.signOut()
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            db.createUser(email, password).addOnCompleteListener { task ->
                with(task) {
                    addOnSuccessListener {
                        _answerRequestFromDB.value = Result.success("Ok")
                    }
                    addOnCanceledListener {
                        _answerRequestFromDB.value = Result.failure(Exception())
                    }
                    addOnFailureListener {
                        _answerRequestFromDB.value = Result.failure(Exception())
                    }
                }
            }

        }
    }

    /*  fun signInWithGoogle(idToken: String) {
          viewModelScope.launch {
              val task = db.signInWithGoogle(idToken)
              with(task) {
                  addOnCompleteListener { _answerRequestSignIn.value = Result.success("OK") }
                  addOnCanceledListener {
                      _answerRequestSignIn.value =
                          Result.failure(Exception())
                  }
                  addOnFailureListener {
                      _answerRequestSignIn.value =
                          Result.failure(Exception())
                  }
              }

          }
      }*/

    private fun openMainFragment() {
        _openMainFragmentEvent.value = true
    }

    private fun showMessage(message: String) {
        // _AnswerRequestSignIn.value = message
    }

}