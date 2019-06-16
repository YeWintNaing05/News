package com.ywn.data.mapper

import com.ywn.data.Mapper
import com.ywn.data.entity.ArticleEntity
import com.ywn.domain.model.Article
import javax.inject.Inject

class ArticleEntityMapper @Inject constructor(
    private val sourceEntityMapper: SourceEntityMapper
) : Mapper<Article, ArticleEntity>() {

    override fun map(entity: ArticleEntity): Article {
        return Article(
            sourceEntityMapper.map(entity.source),
            entity.author,
            entity.title,
            entity.description,
            entity.url,
            entity.urlToImage,
            entity.publishedAt,
            entity.content
        )
    }

    fun map(items: List<ArticleEntity>): List<Article> {
        val articles = ArrayList<Article>(items.size)
        for (response in items)
            articles.add(map(response))

        return articles

    }
}