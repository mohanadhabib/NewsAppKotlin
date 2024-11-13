package com.mohanad.newsappkotlin.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @SerializedName("status")
    val status:String,

    @SerializedName("totalResults")
    val totalResults:Int,

    @SerializedName("articles")
    val articles:List<News>?,

)
