package com.mohanad.newsappkotlin.data.datasource.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFirebase {

    // Login and authenticating user from firebase
    fun login(
        email: String,
        password: String,
        onSuccess: (AuthResult) -> Unit,
        onFailure: (Exception) -> Unit,
        onExceptionFound: (Exception) -> Unit
    ) {
        try {
            Firebase.auth.signInWithEmailAndPassword(
                email, password
            ).addOnSuccessListener {
                onSuccess(it)
            }.addOnFailureListener {
                onFailure(it)
            }
        } catch (e: Exception) {
            onExceptionFound(e)
        }
    }
}