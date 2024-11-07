package com.mohanad.newsappkotlin.data.model.repository

import android.net.Uri
import com.google.firebase.storage.UploadTask
import com.mohanad.newsappkotlin.data.datasource.firebase.FillProfileFirebase


class FillProfileRepository {

    // store the user selected image into firebase storage
    fun storeUserImage(imageUrl: Uri, onSuccess :(UploadTask.TaskSnapshot?)-> Unit, onFailure:(Exception)->Unit ){
        FillProfileFirebase.storeUserImage(
            imageUrl = imageUrl,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    // get the uri of stored user image from the firebase storage
    fun getUserImageAsUri(onSuccess: (Uri?) -> Unit , onFailure: (Exception) -> Unit){
        FillProfileFirebase.getUserImageAsUri(
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    // store all the rest of user info like name and phone
    fun storeAllUserData(name:String , fullName:String , phone:String , imageUrl: Uri , onSuccess: (Void?) -> Unit , onFailure: (Exception) -> Unit){
        FillProfileFirebase.storeAllUserData(
            name = name,
            fullName = fullName,
            phone = phone,
            imageUrl = imageUrl,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}