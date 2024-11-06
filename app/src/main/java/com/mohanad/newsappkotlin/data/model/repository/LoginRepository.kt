package com.mohanad.newsappkotlin.data.model.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mohanad.newsappkotlin.data.datasource.sharedpreferences.UserSharedPreferences

class LoginRepository (private val context:Context){

    // The stored userId in shared preferences
    val storedId = MutableLiveData<String>(UserSharedPreferences.getStoredId(context))

    // Login and authenticating user from firebase
    fun login(email: String, password: String, onSuccess: (AuthResult)-> Unit, onFailure: (Exception)-> Unit,onExceptionFound:(Exception)->Unit ){
        try{
            Firebase.auth.signInWithEmailAndPassword(
                email,password
            ).addOnSuccessListener{
                onSuccess(it)
            }.addOnFailureListener{
                onFailure(it)
            }
        }catch (e:Exception){
            onExceptionFound(e)
        }
    }

    // Storing the userId in shared preferences
    fun storeId(id:String){
        UserSharedPreferences.storeId(
            context = context,
            id =id
        )
    }
}