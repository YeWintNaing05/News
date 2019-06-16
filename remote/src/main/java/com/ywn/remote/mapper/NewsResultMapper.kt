package com.ywn.remote.mapper

import com.ywn.data.entity.NewsEntity
import com.ywn.remote.Mapper
import com.ywn.remote.entity.NewsResponse
import javax.inject.Inject

class NewsResultMapper @Inject constructor(private val articleResultMapper: ArticleResultMapper) :
    Mapper<NewsResponse, NewsEntity> {

    override fun map(item: NewsResponse): NewsEntity {
        return NewsEntity(item.status, item.totalResults, articleResultMapper.map(item.articles!!))
    }
}