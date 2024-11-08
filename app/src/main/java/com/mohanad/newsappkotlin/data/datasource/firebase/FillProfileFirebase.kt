package com.mohanad.newsappkotlin.data.datasource.firebase

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.storage
import com.mohanad.newsappkotlin.util.FirebaseConstants

class FillProfileFirebase {

    val id = Firebase.auth.uid

    // store the user selected image into firebase storage
    fun storeUserImage(
        imageUrl: Uri,
        onSuccess: (UploadTask.TaskSnapshot?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            Firebase.storage.getReference(id!!).putFile(imageUrl)
                .addOnSuccessListener { onSuccess(it) }
                .addOnFailureListener { onFailure(it) }
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    // get the uri of stored user image from the firebase storage
    fun getUserImageAsUri(onSuccess: (Uri?) -> Unit, onFailure: (Exception) -> Unit) {
        try {
            Firebase.storage.getReference(id!!).downloadUrl
                .addOnSuccessListener { onSuccess(it) }
                .addOnFailureListener { onFailure(it) }
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    // store all the rest of user info like name and phone
    fun storeAllUserData(
        name: String,
        fullName: String,
        phone: String,
        imageUrl: Uri,
        onSuccess: (Void?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            Firebase.firestore
                .collection(FirebaseConstants.USER_COLLECTION)
                .document(id!!)
                .update(
                    mapOf(
                        "userName" to name,
                        "userFullName" to fullName,
                        "phoneNumber" to phone,
                        "userImg" to imageUrl
                    )
                )
                .addOnSuccessListener { onSuccess(it) }
                .addOnFailureListener { onFailure(it) }
        } catch (e: Exception) {
            onFailure(e)
        }
    }
}