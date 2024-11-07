package com.mohanad.newsappkotlin.data.model.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.mohanad.newsappkotlin.data.datasource.firebase.LoginFirebase
import com.mohanad.newsappkotlin.data.datasource.sharedpreferences.UserSharedPreferences

class LoginRepository (private val context:Context){

    // The stored userId in shared preferences
    val storedId = MutableLiveData<String>(UserSharedPreferences.getStoredId(context))

    // Login and authenticating user from firebase
    fun login(email: String, password: String, onSuccess: (AuthResult)-> Unit, onFailure: (Exception)-> Unit,onExceptionFound:(Exception)->Unit ){
        LoginFirebase.login(
            email = email,
            password = password,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onExceptionFound = onExceptionFound
        )
    }

    // Storing the userId in shared preferences
    fun storeId(id:String){
        UserSharedPreferences.storeId(
            context = context,
            id =id
        )
    }
}