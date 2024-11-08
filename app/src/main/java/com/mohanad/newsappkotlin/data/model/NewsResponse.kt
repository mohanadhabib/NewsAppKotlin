package com.mohanad.newsappkotlin.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @SerializedName("status")
    val status:String?,

    @SerializedName("totalResults")
    val totalResults:Long?,

    @SerializedName("results")
    val results:List<News>?,

    @SerializedName("nextPage")
    val nextPage:String?

)
