package com.ywn.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "totalResults")
    val totalResults: Int? = null,
    @Json(name = "articles")
    val articles: List<ArticleResponse>? = null
)