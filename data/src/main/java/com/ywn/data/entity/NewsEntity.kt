package com.ywn.data.entity

data class NewsEntity(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<ArticleEntity>? = null
)