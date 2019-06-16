package com.ywn.data.datasource.top_headlines

import com.ywn.data.entity.NewsEntity
import io.reactivex.Single


interface TopHeadlinesDataSource {

    fun getTopNewsHeadlines(
        country: String,
        apiKey: String
    ): Single<NewsEntity>

    fun getHeadlinesWithSpecificSource(
        sources: String,
        apiKey: String
    ): Single<NewsEntity>


}