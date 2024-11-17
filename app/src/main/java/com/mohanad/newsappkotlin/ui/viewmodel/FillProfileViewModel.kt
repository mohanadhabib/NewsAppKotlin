package com.mohanad.newsappkotlin.ui.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.UploadTask
import com.mohanad.newsappkotlin.data.model.repository.FillProfileRepository

class FillProfileViewModel :ViewModel() {
    // Repository instance
    private val repository = FillProfileRepository ()
    // State variables
    var imageUrl: Uri? by mutableStateOf(Uri.EMPTY)
    var userNameTextField by mutableStateOf("")
    var fullNameTextField by mutableStateOf("")
    var phoneNumberTextField by mutableStateOf("")
    var isNameError by mutableStateOf(false)
    var isFullNameError by  mutableStateOf(false)
    var isPhoneNumberError by  mutableStateOf(false)
    // Store the user selected image into firebase storage
    fun storeUserImage(imageUrl: Uri, onSuccess :(UploadTask.TaskSnapshot?)-> Unit, onFailure:(Exception)->Unit ){
        repository.storeUserImage(
            imageUrl = imageUrl,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
    // Get the uri of stored user image from the firebase storage
    fun getUserImageAsUri(onSuccess: (Uri?) -> Unit , onFailure: (Exception) -> Unit){
        repository.getUserImageAsUri(
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
    // Store all the rest of user info like name and phone
    fun storeAllUserData(name:String , fullName:String , phone:String , imageUrl: Uri , onSuccess: (Void?) -> Unit , onFailure: (Exception) -> Unit){
        repository.storeAllUserData(
            name = name,
            fullName = fullName,
            phone = phone,
            imageUrl = imageUrl,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}