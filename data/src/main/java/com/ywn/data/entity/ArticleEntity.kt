package com.ywn.data.entity

data class ArticleEntity(
    val source: SourceEntity,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String?
)