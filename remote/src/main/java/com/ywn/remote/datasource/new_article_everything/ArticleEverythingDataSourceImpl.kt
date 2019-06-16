package com.ywn.remote.datasource.new_article_everything

import com.ywn.data.datasource.new_article_everything.ArticleEverythingDataSource
import com.ywn.data.entity.NewsEntity
import com.ywn.remote.mapper.NewsResultMapper
import com.ywn.remote.network.NewsApi
import io.reactivex.Single
import javax.inject.Inject
import com.ywn.data.exception.Issue
import com.ywn.data.exception.DataException
import java.io.IOException

class ArticleEverythingDataSourceImpl
@Inject constructor(
    private val newsApi: NewsApi,
    private val newsResultMapper: NewsResultMapper
) : ArticleEverythingDataSource {
    override fun getNewArticlesWithSpecificTopic(q: String, apiKey: String): Single<NewsEntity> {
        return Single.defer<NewsEntity> {
            try {
                val response = newsApi.getNewArticlesWithSpecificTopic(q, apiKey).execute()
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