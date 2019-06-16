package com.ywn.common.model

data class ArticleViewItem(
    val source: SourceViewItem,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String?
)