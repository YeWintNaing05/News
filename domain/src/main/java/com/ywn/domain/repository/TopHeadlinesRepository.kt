package com.ywn.domain.repository

import com.ywn.domain.model.News
import io.reactivex.Single

interface TopHeadlinesRepository {

    fun getTopNewsHeadlines(
        country: String,
        apiKey: String
    ): Single<News>

    fun getHeadlinesWithSpecificSource(
        sources: String,
        apiKey: String
    ): Single<News>

}