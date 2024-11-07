package com.mohanad.newsappkotlin.data.model.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.mohanad.newsappkotlin.data.datasource.firebase.SignUpFirebase
import com.mohanad.newsappkotlin.data.datasource.sharedpreferences.UserSharedPreferences
import com.mohanad.newsappkotlin.data.model.User

class SignUpRepository (private val context: Context){

    // The stored userId in shared preferences
    val storedId = MutableLiveData<String>(UserSharedPreferences.getStoredId(context))

    // Signup and authenticating user from firebase
    fun signUp(email:String , password:String , onSuccess:(AuthResult)-> Unit , onFailure:(Exception)-> Unit,onExceptionFound:(Exception)->Unit){
        SignUpFirebase.signUp(
            email = email,
            password = password,
            onSuccess = onSuccess,
            onFailure = onFailure,
             onExceptionFound = onExceptionFound
        )
    }

    // Storing user data into firebase firestore
    fun createUser(user: User , onSuccess: (Void?) -> Unit , onFailure: (Exception) -> Unit,onExceptionFound:(Exception)->Unit){
        SignUpFirebase.createUser(
            user = user,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onExceptionFound = onExceptionFound
        )
    }

    // Storing the userId in shared preferences
    fun storeId(id:String){
        UserSharedPreferences.storeId(
            context = context,
            id = id
        )
    }

}


