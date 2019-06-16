package com.ywn.remote.mapper

import com.ywn.data.entity.ArticleEntity
import com.ywn.remote.Mapper
import com.ywn.remote.entity.ArticleResponse
import javax.inject.Inject

class ArticleResultMapper
@Inject constructor(private val sourceResultMapper: SourceResultMapper) : Mapper<ArticleResponse, ArticleEntity> {

    override fun map(item: ArticleResponse): ArticleEntity {
        return ArticleEntity(
            sourceResultMapper.map(item.source),
            item.author,
            item.title!!,
            item.description!!,
            item.url!!,
            item.urlToImage!!,
            item.publishedAt!!,
            item.content
        )
    }

    fun map(items: List<ArticleResponse>): List<ArticleEntity> {
        val articles = ArrayList<ArticleEntity>(items.size)
        for (response in items)
            articles.add(map(response))

        return articles

    }
}