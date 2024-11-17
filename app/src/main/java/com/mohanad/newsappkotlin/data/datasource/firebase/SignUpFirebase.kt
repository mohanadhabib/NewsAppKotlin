package com.mohanad.newsappkotlin.data.datasource.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mohanad.newsappkotlin.data.model.User
import com.mohanad.newsappkotlin.util.FirebaseConstants

class SignUpFirebase {
    // Signup and authenticating user from firebase
    fun signUp(
        email: String,
        password: String,
        onSuccess: (AuthResult) -> Unit,
        onFailure: (Exception) -> Unit,
        onExceptionFound: (Exception) -> Unit
    ) {
        try {
            Firebase.auth
                .createUserWithEmailAndPassword(
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
    // Storing user data into firebase firestore
    fun createUser(
        user: User,
        onSuccess: (Void?) -> Unit,
        onFailure: (Exception) -> Unit,
        onExceptionFound: (Exception) -> Unit
    ) {
        try {
            Firebase.firestore
                .collection(FirebaseConstants.USER_COLLECTION)
                .document(user.userId ?: "")
                .set(user)
                .addOnSuccessListener {
                    onSuccess(it)
                }.addOnFailureListener {
                    onFailure(it)
                }
        } catch (e: Exception) {
            onExceptionFound(e)
        }
    }
}