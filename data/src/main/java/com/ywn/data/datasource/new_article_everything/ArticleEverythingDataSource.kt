package com.ywn.data.datasource.new_article_everything

import com.ywn.data.entity.NewsEntity
import io.reactivex.Single

interface ArticleEverythingDataSource {

    fun getNewArticlesWithSpecificTopic(
        q: String,
        apiKey: String
    ): Single<NewsEntity>
}