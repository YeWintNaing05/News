package com.ywn.data.repository

import com.ywn.data.datasource.top_headlines.TopHeadlinesDataSource
import com.ywn.data.mapper.NewsEntityMapper
import com.ywn.domain.model.News
import com.ywn.domain.repository.TopHeadlinesRepository
import io.reactivex.Single
import javax.inject.Inject

class TopHeadlinesRepositoryImpl @Inject constructor(
    private val topHeadlinesDataSource: TopHeadlinesDataSource,
    private val newsEntityMapper: NewsEntityMapper
) : TopHeadlinesRepository {

    override fun getHeadlinesWithSpecificSource(sources: String, apiKey: String): Single<News> {
        return topHeadlinesDataSource.getHeadlinesWithSpecificSource(sources, apiKey)
            .map(newsEntityMapper::map)
    }

    override fun getTopNewsHeadlines(country: String, apiKey: String): Single<News> {
        return topHeadlinesDataSource.getTopNewsHeadlines(country, apiKey).map(newsEntityMapper::map)
    }

}