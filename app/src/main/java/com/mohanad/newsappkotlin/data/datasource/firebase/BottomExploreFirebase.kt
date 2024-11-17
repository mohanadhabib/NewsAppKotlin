package com.mohanad.newsappkotlin.data.datasource.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mohanad.newsappkotlin.data.model.User
import com.mohanad.newsappkotlin.util.FirebaseConstants

class BottomExploreFirebase {
    // Get unselected topics from the startup choose topics screen
    fun getSuggestedTopics(onFailure:(Exception)->Unit) :List<String>{
        val userId = Firebase.auth.currentUser?.uid ?: ""
        val suggestionList = mutableListOf<String>()
        val topicsList = listOf("Top" , "National" , "Sports" ,"Lifestyle" , "Business" , "Health" , "Fashion" , "Technology" , "Science" , "Arts" , "Politics")
        Firebase.firestore
            .collection(FirebaseConstants.USER_COLLECTION)
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                val userTopicsList = document.toObject(User::class.java)?.listOfTopics
                topicsList.forEach { item ->
                    if(!userTopicsList?.contains(item)!!){
                        suggestionList.add(item)
                    }
                }
            }
            .addOnFailureListener { onFailure(it) }
        return suggestionList
    }
}