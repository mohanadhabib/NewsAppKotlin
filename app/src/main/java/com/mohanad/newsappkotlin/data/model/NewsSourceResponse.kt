package com.mohanad.newsappkotlin.data.model

import com.google.gson.annotations.SerializedName

// The whole news source response model parsed from news api
data class NewsSourceResponse (
    @SerializedName("status")
    val status:String,

    @SerializedName("totalResults")
    val totalResults:Int,

    @SerializedName("results")
    val results:List<NewsSource>,

    @SerializedName("nextPage")
    val nextPage:String?
)