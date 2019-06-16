package com.ywn.common.model

import com.ywn.common.base.mapper.ViewItem
import com.ywn.common.base.mapper.Visibility

data class NewsViewItem(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<ArticleViewItem>? = null,
    @Visibility val articlesVisibility: Int,
    override val progressVisibility: Int,
    override val errorVisibility: Int,
    override val errorIncludeData: Boolean,
    override val retryVisibility: Int,
    override val shouldRetry: Boolean,
    override val errorActionMessage: String,
    override val errorMessage: String
) : ViewItem