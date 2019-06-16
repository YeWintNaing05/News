package com.ywn.data.repository

import com.ywn.data.datasource.new_article_everything.ArticleEverythingDataSource
import com.ywn.data.mapper.NewsEntityMapper
import com.ywn.domain.model.News
import com.ywn.domain.repository.ArticleRepository
import io.reactivex.Single
import javax.inject.Inject

class ArticleRepositoryImpl
@Inject constructor(
    private val articleEverythingDataSource: ArticleEverythingDataSource,
    private val newsEntityMapper: NewsEntityMapper
) : ArticleRepository {

    override fun getNewArticlesWithSpecificTopic(q: String, apiKey: String): Single<News> {
        return articleEverythingDataSource.getNewArticlesWithSpecificTopic(q, apiKey).map(newsEntityMapper::map)
    }

}