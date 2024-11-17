package com.mohanad.newsappkotlin.data.datasource.firebase

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.storage
import com.mohanad.newsappkotlin.util.FirebaseConstants

class EditProfileFirebase {
    // Current stored user id
    private val id = Firebase.auth.uid ?: ""
    // Store the user selected image into firebase storage
    fun storeUserImage(
        imageUrl: Uri,
        onSuccess: (UploadTask.TaskSnapshot?) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            Firebase.storage.getReference(id).putFile(imageUrl)
                .addOnSuccessListener { onSuccess(it) }
                .addOnFailureListener { onFailure(it) }
        } catch (e: Exception) {
            onFailure(e)
        }
    }
    // Get the uri of stored user image from the firebase storage
    fun getUserImageAsUri(onSuccess: (Uri?) -> Unit, onFailure: (Exception) -> Unit) {
        try {
            Firebase.storage.getReference(id).downloadUrl
                .addOnSuccessListener { onSuccess(it) }
                .addOnFailureListener { onFailure(it) }
        } catch (e: Exception) {
            onFailure(e)
        }
    }
    // Update the current user info in both auth and firestore
    @Suppress("DEPRECATION")
    fun updateUserInfo(
        name: String,
        fullName: String,
        email: String,
        phone: String,
        imageUrl: Uri,
        onSuccess: (Void?) -> Unit,
        onFailure: (Exception) -> Unit,
        onEmailUpdateFailure: () -> Unit
    ) {
        Firebase.auth.currentUser?.updateEmail(email)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Firebase.firestore.collection(FirebaseConstants.USER_COLLECTION)
                        .document(id)
                        .update(
                            mapOf(
                                "userName" to name,
                                "userFullName" to fullName,
                                "userEmail" to email,
                                "phoneNumber" to phone,
                                "userImg" to imageUrl
                            )
                        )
                        .addOnSuccessListener { onSuccess(it) }
                        .addOnFailureListener { onFailure(it) }
                } else {
                    onEmailUpdateFailure()
                }
            }
    }
}