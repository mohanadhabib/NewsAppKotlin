package com.mohanad.newsappkotlin.data.datasource.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mohanad.newsappkotlin.util.FirebaseConstants

class TopicsFirebase {

    // Storing selected topics in firebase firestore
    fun storeTopics(
        list: List<String>,
        onSuccess: (Void?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val id = Firebase.auth.uid
        try {
            Firebase.firestore
                .collection(FirebaseConstants.USER_COLLECTION)
                .document(id!!)
                .update(mapOf("listOfTopics" to list))
                .addOnSuccessListener { onSuccess(it) }
                .addOnFailureListener { onFailure(it) }
        } catch (e: Exception) {
            onFailure(e)
        }
    }
}