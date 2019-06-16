package com.ywn.common.mapper

import android.content.Context
import android.view.View
import com.ywn.common.R
import com.ywn.common.base.mapper.ViewStateMapper
import com.ywn.common.exception.IssueFactory
import com.ywn.common.model.ArticleViewItem
import com.ywn.common.model.NewsViewItem
import com.ywn.data.exception.DataException
import com.ywn.domain.model.News
import com.ywn.domain.model.State
import java.util.ArrayList
import javax.inject.Inject

class NewsViewItemMapper @Inject constructor(
    private val issueFactory: IssueFactory,
    private val articleViewItemMapper: ArticleViewItemMapper,
    context: Context
) : ViewStateMapper<NewsViewItem, News>(context) {

    override fun map(domainModel: News): NewsViewItem {
        var errorIncludeData = false
        var shouldRetry = false
        var progressVisibility = View.GONE

        var errorActionMessage = ""
        var errorVisibility = View.GONE
        var retryVisibility = View.INVISIBLE
        var articlesVisibility = View.GONE
        var errorMessage = ""
        var retryMessage = ""
        var articles: List<ArticleViewItem> = ArrayList()

        when (domainModel.state) {
            State.SUCCESS -> {
                articlesVisibility = View.VISIBLE
                articles = articleViewItemMapper.map(domainModel.articles!!)

            }
            State.PROGRESS -> {
                progressVisibility = View.VISIBLE
                if (domainModel.articles != null && domainModel.articles!!.isNotEmpty()) {
                    articles = articleViewItemMapper.map(domainModel.articles!!)
                    articlesVisibility = View.VISIBLE
                }
            }
            State.ERROR -> {
                errorVisibility = View.VISIBLE
                errorActionMessage = context.getString(R.string.error_action_message_ok)

                if (domainModel.throwable != null)
                    if (domainModel.throwable is DataException) {
                        val de = domainModel.throwable as DataException
                        if (de.shouldRetry()) {
                            errorActionMessage = context.getString(R.string.error_action_message_retry)
                            retryVisibility = View.VISIBLE
                            shouldRetry = true
                        }
                        errorMessage = issueFactory.getMessage(de.issue)
                    } else {
                        errorMessage = domainModel.throwable!!.message!!
                    }

                if (domainModel.articles != null && domainModel.articles!!.isNotEmpty()) {
                    articles = articleViewItemMapper.map(domainModel.articles!!)
                    articlesVisibility = View.VISIBLE
                    errorVisibility = View.GONE
                    retryVisibility = View.INVISIBLE
                    errorIncludeData = true
                }
            }
            else -> progressVisibility = View.VISIBLE
        }

        return NewsViewItem(
            domainModel.status,
            domainModel.totalResults,
            articles,
            articlesVisibility,
            progressVisibility,
            errorVisibility,
            errorIncludeData,
            retryVisibility,
            shouldRetry,
            errorActionMessage,
            errorMessage

        )

    }

}