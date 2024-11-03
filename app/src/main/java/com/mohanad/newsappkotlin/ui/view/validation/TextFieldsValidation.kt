package com.mohanad.newsappkotlin.ui.view.validation

var emailErrorText = ""

var passwordErrorText = ""

fun emailValidation(email:String): Boolean{

    val isError = if(email.isEmpty()){
        emailErrorText =  "Email is empty"
        true
    }
    else if(!email.contains("@")){
        emailErrorText = "Not email format"
        true
    }
    else{
        false
    }
    return isError
}

fun passwordValidation(password:String): Boolean{

    val isError = if(password.isEmpty()){
        passwordErrorText = "Password is empty"
        true
    }
    else if(password.length < 8){
        passwordErrorText = "Password is not strong, it must be more than 8 letters"
        true
    }
    else{
        false
    }
    return isError
}
