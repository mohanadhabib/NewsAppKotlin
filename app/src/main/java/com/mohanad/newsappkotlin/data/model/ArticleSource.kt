package com.mohanad.newsappkotlin.data.model

import com.google.gson.annotations.SerializedName

data class ArticleSource(
    @SerializedName("id")
    val id:String?,

    @SerializedName("name")
    val name:String
)
