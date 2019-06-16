package com.ywn.domain.repository

import com.ywn.domain.model.News
import io.reactivex.Single

interface ArticleRepository {
    fun getNewArticlesWithSpecificTopic(
        q: String,
        apiKey: String
    ): Single<News>
}