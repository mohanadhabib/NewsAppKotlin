package com.mohanad.newsappkotlin.data.model

import com.google.gson.annotations.SerializedName

// Country model parsed from countries api
data class Country (
    @SerializedName("name")
    val name:String,

    @SerializedName("code")
    val code:String,

    @SerializedName("emoji")
    val emoji:String,

    @SerializedName("unicode")
    val unicode:String,

    @SerializedName("image")
    val image:String,

    @SerializedName("dial_code")
    val dialCode:String
)