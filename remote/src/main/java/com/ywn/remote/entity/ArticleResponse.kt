package com.ywn.remote.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.ywn.remote.network.NullToEmptyString


@JsonClass(generateAdapter = true)
data class ArticleResponse(
    @Json(name = "source")
    val source: SourceResponse,
    @Json(name = "author")
    @NullToEmptyString
    val author: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "description")
    @NullToEmptyString
    val description: String?,
    @Json(name = "url")
    val url: String?,
    @NullToEmptyString
    @Json(name = "urlToImage")
    val urlToImage: String?,
    @Json(name = "publishedAt")
    val publishedAt: String?,
    @Json(name = "content")
    @NullToEmptyString val content: String?
)