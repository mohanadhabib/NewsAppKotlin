package com.mohanad.newsappkotlin.data.model.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mohanad.newsappkotlin.data.model.User
import com.mohanad.newsappkotlin.util.FirebaseConstants
import com.mohanad.newsappkotlin.util.SharedPreferencesConstants

class SignUpRepository (private val context: Context){

    val storedId = MutableLiveData<String>(context.getSharedPreferences(SharedPreferencesConstants.USER_SHARED_PREFERENCES,0).getString(SharedPreferencesConstants.USER,""))

    fun signUp(email:String , password:String , onSuccess:(AuthResult)-> Unit , onFailure:(Exception)-> Unit){
        Firebase.auth
            .createUserWithEmailAndPassword(
                email,password
            ).addOnSuccessListener{
                onSuccess(it)
            }.addOnFailureListener{
                onFailure(it)
            }
    }

    fun createUser(user: User , onSuccess: (Void?) -> Unit , onFailure: (Exception) -> Unit){
        Firebase.firestore
            .collection(FirebaseConstants.USER_COLLECTION)
            .document(user.userId)
            .set(user)
            .addOnSuccessListener{
                onSuccess(it)
            }.addOnFailureListener{
                onFailure(it)
            }
    }

    fun storeId(id:String){
        val preferences = context.getSharedPreferences(SharedPreferencesConstants.USER_SHARED_PREFERENCES,0).edit()
        preferences.putString(SharedPreferencesConstants.USER,id).apply()
    }

}


