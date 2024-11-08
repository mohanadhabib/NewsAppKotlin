package com.mohanad.newsappkotlin.data.datasource.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mohanad.newsappkotlin.util.FirebaseConstants

class SelectCountryFirebase {

    // Storing the country name into the user info in firestore
    fun storeUserCountry(
        countryName: String,
        onSuccess: (Void?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            Firebase.firestore.collection(FirebaseConstants.USER_COLLECTION)
                .document(Firebase.auth.uid!!)
                .update(
                    mapOf(pair = "country" to countryName)
                ).addOnSuccessListener {
                    onSuccess(it)
                }
                .addOnFailureListener {
                    onFailure(it)
                }
        } catch (e: Exception) {
            onFailure(e)
        }
    }
}