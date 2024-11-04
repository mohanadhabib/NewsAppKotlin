package com.mohanad.newsappkotlin.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.firebase.auth.AuthResult
import com.mohanad.newsappkotlin.data.model.User
import com.mohanad.newsappkotlin.data.model.repository.SignUpRepository

class SignUpViewModel(application: Application) : AndroidViewModel(application = application) {

    private val repository = SignUpRepository(application.applicationContext)

    // The stored userId in repository
    val storedId:LiveData<String> = repository.storedId

    // Signup and authenticating user in repository
    fun signUp(email:String , password:String , onSuccess:(AuthResult) -> Unit , onFailure:(Exception) -> Unit,onExceptionFound:(Exception)-> Unit){
        repository.signUp(
            email = email,
            password = password,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onExceptionFound = onExceptionFound
        )
    }

    // Storing user info in repository
    fun createUser(user: User , onSuccess: (Void?) -> Unit , onFailure: (Exception) -> Unit,onExceptionFound:(Exception)-> Unit) {
        repository.createUser(
            user = user,
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