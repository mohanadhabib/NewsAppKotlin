package com.mohanad.newsappkotlin.data.model.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.mohanad.newsappkotlin.data.datasource.firebase.SignUpFirebase
import com.mohanad.newsappkotlin.data.datasource.sharedpreferences.UserSharedPreferences
import com.mohanad.newsappkotlin.data.model.User

class SignUpRepository (private val context: Context){
    // Data source instances
    private val firebase = SignUpFirebase()
    private val sharedPreferences = UserSharedPreferences()
    // The stored userId in shared preferences
    val storedId = MutableLiveData<String>(sharedPreferences.getStoredId(context))
    // Signup and authenticating user from firebase
    fun signUp(email:String , password:String , onSuccess:(AuthResult)-> Unit , onFailure:(Exception)-> Unit,onExceptionFound:(Exception)->Unit){
        firebase.signUp(
            email = email,
            password = password,
            onSuccess = onSuccess,
            onFailure = onFailure,
             onExceptionFound = onExceptionFound
        )
    }
    // Storing user data into firebase firestore
    fun createUser(user: User , onSuccess: (Void?) -> Unit , onFailure: (Exception) -> Unit,onExceptionFound:(Exception)->Unit){
        firebase.createUser(
            user = user,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onExceptionFound = onExceptionFound
        )
    }
    // Storing the userId in shared preferences
    fun storeId(id:String){
        sharedPreferences.storeId(
            context = context,
            id = id
        )
    }
}


