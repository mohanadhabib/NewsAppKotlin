package com.mohanad.newsappkotlin.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.firebase.auth.AuthResult
import com.mohanad.newsappkotlin.data.model.repository.LoginRepository

class LoginViewModel (application: Application): AndroidViewModel(application = application) {
    // Repository instance
    private val repository = LoginRepository(application.applicationContext)
    // State variables
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isChecked by mutableStateOf(false)
    var isEmailError by mutableStateOf(false)
    var isPasswordError by mutableStateOf(false)
    var isPasswordShown by mutableStateOf(true)
    var passwordHide by mutableStateOf(VisualTransformation.None)
    // The Stored userId in repository
    val storedId: LiveData<String> = repository.storedId
    // Login and authenticating user in repository
    fun login(email:String, password: String, onSuccess: (AuthResult)->Unit, onFailure: (Exception)->Unit,onExceptionFound:(Exception)->Unit){
        repository.login(
            email = email,
            password = password,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onExceptionFound = onExceptionFound
        )
    }
    // Storing userId in repository
    fun storeId(id:String){
        repository.storeId(id)
    }
}