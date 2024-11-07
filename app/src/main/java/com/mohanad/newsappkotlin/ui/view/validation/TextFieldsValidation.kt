package com.mohanad.newsappkotlin.ui.view.validation

// email error text shown under the email text field
var emailErrorText = ""

// password error text shown under the password text field
var passwordErrorText = ""

// name error text shown under the name or full name text field
var nameErrorText = ""

// phone error text shown under the phone number text field
var phoneErrorText = ""

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

// name validation function
fun nameValidation(name:String): Boolean{

    val isError = if(name.isEmpty()){
        nameErrorText = "Your name is empty"
        true
    }
    else{
        false
    }
    return isError
}

// phone number validation function
fun phoneValidation(phone:String): Boolean{

    val isError = if(phone.isEmpty()){
        phoneErrorText = "Phone is empty"
        true
    }
    else if(!phone.contains("+")){
        phoneErrorText = "Invalid phone format ,Phone must start with + and your country code"
        true
    }
    else{
        false
    }
    return isError
}