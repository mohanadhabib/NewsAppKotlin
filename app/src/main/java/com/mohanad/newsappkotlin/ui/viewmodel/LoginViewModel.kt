package com.mohanad.newsappkotlin.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.firebase.auth.AuthResult
import com.mohanad.newsappkotlin.data.model.repository.LoginRepository

class LoginViewModel (application: Application): AndroidViewModel(application = application) {

    private val repository = LoginRepository(application.applicationContext)

    val storedId: LiveData<String> = repository.storedId

    fun login(email:String, password: String, onSuccess: (AuthResult)->Unit, onFailure: (Exception)->Unit){
        repository.login(
            email = email,
            password = password,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
    fun storeId(id:String){
        repository.storeId(id)
    }
}