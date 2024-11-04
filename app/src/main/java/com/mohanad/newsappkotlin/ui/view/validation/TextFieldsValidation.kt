package com.mohanad.newsappkotlin.ui.view.validation

// email error text shown under the email text field
var emailErrorText = ""

// password error text shown under the password text field
var passwordErrorText = ""

// email validation function
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

// password validation function
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
