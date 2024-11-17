package com.mohanad.newsappkotlin.data.model

import com.google.gson.annotations.SerializedName

// Article source model parsed from news source api
data class ArticleSource(
    @SerializedName("id")
    val id:String?,

    @SerializedName("name")
    val name:String?
)
