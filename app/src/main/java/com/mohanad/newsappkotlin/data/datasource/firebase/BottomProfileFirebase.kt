package com.mohanad.newsappkotlin.data.datasource.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.mohanad.newsappkotlin.data.model.User
import com.mohanad.newsappkotlin.util.FirebaseConstants
import kotlinx.coroutines.tasks.await

class BottomProfileFirebase {
    // Get the current user info
    suspend fun getUserInfo(): User? {
        val id = Firebase.auth.currentUser?.uid ?: ""
        return if(id == ""){
            null
        }else{
            Firebase.firestore
                .collection(FirebaseConstants.USER_COLLECTION)
                .document(id)
                .get()
                .await()
                .toObject<User>()
        }
    }
    // Log out the current user
    fun logOut(){
        Firebase.auth.signOut()
    }
}