package com.mohanad.newsappkotlin.data.model.repository

import android.net.Uri
import com.google.firebase.storage.UploadTask
import com.mohanad.newsappkotlin.data.datasource.firebase.EditProfileFirebase

class EditProfileRepository {
    // Firebase instance of editing profile view
    private val firebase = EditProfileFirebase()
    // Store the user selected image into firebase storage
    fun storeUserImage(
        imageUrl: Uri,
        onSuccess: (UploadTask.TaskSnapshot?) -> Unit,
        onFailure: (Exception) -> Unit
    ){
        firebase.storeUserImage(
            imageUrl = imageUrl,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
    // Get the uri of stored user image from the firebase storage
    fun getUserImageAsUri(onSuccess: (Uri?) -> Unit, onFailure: (Exception) -> Unit) {
        firebase.getUserImageAsUri(
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
    // Update the current user info in both auth and firestore
    fun updateUserInfo(
        name: String,
        fullName: String,
        email:String,
        phone: String,
        imageUrl: Uri,
        onSuccess: (Void?) -> Unit,
        onFailure: (Exception) -> Unit,
        onEmailUpdateFailure: () -> Unit
    ){
        firebase.updateUserInfo(
            name = name,
            fullName = fullName,
            email = email,
            phone = phone,
            imageUrl = imageUrl,
            onSuccess = onSuccess,
            onFailure = onFailure,
            onEmailUpdateFailure = onEmailUpdateFailure
        )
    }

}