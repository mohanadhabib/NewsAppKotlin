package com.mohanad.newsappkotlin.data.datasource.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mohanad.newsappkotlin.util.FirebaseConstants

class NewsSourceFirebase {
    // Store user's preferred news sources into firebase firestore
    fun storeNewsSources(
        list: List<String>,
        onSuccess: (Void?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            val id = Firebase.auth.uid
            Firebase.firestore
                .collection(FirebaseConstants.USER_COLLECTION)
                .document(id!!)
                .update(mapOf("newsSource" to list))
                .addOnSuccessListener { onSuccess(it) }
                .addOnFailureListener { onFailure(it) }
        } catch (e: Exception) {
            onFailure(e)
        }
    }
}