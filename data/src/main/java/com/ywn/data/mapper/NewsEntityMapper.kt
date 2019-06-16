package com.ywn.data.mapper

import com.ywn.data.Mapper
import com.ywn.data.entity.NewsEntity
import com.ywn.domain.model.News
import javax.inject.Inject

class NewsEntityMapper @Inject constructor(
    private val articleEntityMapper: ArticleEntityMapper
) : Mapper<News, NewsEntity>() {
    override fun map(entity: NewsEntity): News {
        return News.success(
            entity.status,
            entity.totalResults,
            articleEntityMapper.map(entity.articles!!)
        )
    }
}