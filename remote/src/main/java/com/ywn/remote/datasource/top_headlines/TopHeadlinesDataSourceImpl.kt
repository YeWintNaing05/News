package com.ywn.remote.datasource.top_headlines

import com.ywn.data.datasource.top_headlines.TopHeadlinesDataSource
import com.ywn.data.entity.NewsEntity
import com.ywn.data.exception.DataException
import com.ywn.data.exception.Issue
import com.ywn.remote.mapper.NewsResultMapper
import com.ywn.remote.network.NewsApi
import io.reactivex.Single
import java.io.IOException
import javax.inject.Inject

class TopHeadlinesDataSourceImpl
@Inject constructor(
    private val newsApi: NewsApi,
    private val newsResultMapper: NewsResultMapper
) : TopHeadlinesDataSource {

    override fun getHeadlinesWithSpecificSource(sources: String, apiKey: String): Single<NewsEntity> {
        return Single.defer<NewsEntity> {
            try {
                val response = newsApi.getHeadlinesWithSpecificSource(sources, apiKey).execute()
                if (response.isSuccessful) {
                    return@defer Single.just(newsResultMapper.map(response.body()!!))
                } else {
                    return@defer Single.error(DataException(Issue.API))
                }
            } catch (e: IOException) {
                return@defer Single.error(DataException(Issue.NETWORK))
            }
        }
    }

    override fun getTopNewsHeadlines(country: String, apiKey: String): Single<NewsEntity> {
        return Single.defer<NewsEntity> {
            try {
                val response = newsApi.getTopNewsHeadlines(country, apiKey).execute()
                if (response.isSuccessful) {
                    return@defer Single.just(newsResultMapper.map(response.body()!!))
                } else {
                    return@defer Single.error(DataException(Issue.API))
                }
            } catch (e: IOException) {
                return@defer Single.error(DataException(Issue.NETWORK))
            }
        }
    }

}