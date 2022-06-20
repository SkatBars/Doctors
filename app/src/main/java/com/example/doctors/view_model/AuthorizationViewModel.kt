package com.example.doctors.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors.datebase.FirebaseAuthDataSource
import com.squareup.okhttp.Response
import kotlinx.coroutines.launch

class AuthorizationViewModel() : ViewModel() {

    private val db = FirebaseAuthDataSource

    private val _openMainFragmentEvent = MutableLiveData<Boolean>(false)
    val openMainFragmentEvent: LiveData<Boolean>
        get() = _openMainFragmentEvent

    private val _answerRequestSignIn = MutableLiveData<Result<String>?>()
    val answerRequestSignIn: LiveData<Result<String>?>
        get() = _answerRequestSignIn

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
            Log.i("QWE", "viewModel1")
            db.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                with(task) {
                    addOnSuccessListener {
                        _answerRequestSignIn.value = Result.success("OK") }
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
        }
    }

    fun signOut() {
        viewModelScope.launch {
            db.signOut()
        }
    }

    fun register(email: String, password: String, repeatPassword: String) {
        if (password == repeatPassword) {
            viewModelScope.launch {
                db.createUser(email, password).addOnCompleteListener { task ->
                    with(task) {
                        addOnCompleteListener { openMainFragment() }
                        addOnCanceledListener { showMessage("Операция была отменена, попробуйте снова") }
                        addOnFailureListener { showMessage("Произошла ошибка, попробуйте снова") }
                    }
                }
            }
        } else {
            showMessage("Пароли не совпадают")
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