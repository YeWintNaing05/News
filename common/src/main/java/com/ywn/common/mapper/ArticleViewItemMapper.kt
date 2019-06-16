package com.ywn.common.mapper

import android.content.Context
import com.ywn.common.base.mapper.ViewStateMapper
import com.ywn.common.model.ArticleViewItem
import com.ywn.domain.model.Article
import javax.inject.Inject

class ArticleViewItemMapper
@Inject constructor(
    private val sourceViewItemMapper: SourceViewItemMapper,
    context: Context
) : ViewStateMapper<ArticleViewItem, Article>(context) {

    override fun map(domainModel: Article): ArticleViewItem =
        ArticleViewItem(
            sourceViewItemMapper.map(domainModel.source),
            domainModel.author,
            domainModel.title,
            domainModel.description,
            domainModel.url,
            domainModel.urlToImage,
            domainModel.publishedAt,
            domainModel.content
        )

    fun map(items: List<Article>): List<ArticleViewItem> {
        val articles = ArrayList<ArticleViewItem>(items.size)
        for (response in items)
            articles.add(map(response))

        return articles

    }
}