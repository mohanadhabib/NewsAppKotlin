package com.mohanad.newsappkotlin.data.model

import com.google.gson.annotations.SerializedName

// Country model parsed from countries api
data class Country (
    @SerializedName("name")
    val name:String,

    @SerializedName("image")
    val image:String
)