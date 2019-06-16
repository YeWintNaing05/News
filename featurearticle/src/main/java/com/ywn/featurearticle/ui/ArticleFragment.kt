package com.ywn.featurearticle.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.ywn.common.base.BaseFragment
import com.ywn.common.model.NewsViewItem
import com.ywn.featurearticle.R
import com.ywn.featurearticle.adapter.ArticleAdapter
import com.ywn.featurearticle.viewmodel.ArticleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_article.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ArticleFragment : BaseFragment() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var articleViewModel: ArticleViewModel

    lateinit var articleAdapter: ArticleAdapter

    override val layoutResource: Int
        get() = R.layout.fragment_article

    lateinit var search: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        articleViewModel = ViewModelProviders.of(this, factory).get(ArticleViewModel::class.java)


        articleAdapter = ArticleAdapter()

        rvArticles.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(context)
        }

        startSubscription()

    }

    private fun startSubscription() {
        add(articleViewModel.streamArticles().subscribe(this::showTopHeadlines))
        add(RxView.clicks(btnRetry).subscribe {
            retry()
        })
        add(
            RxTextView.textChangeEvents(edtSearch)
                .skipInitialValue()
                .debounce(800, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.text().toString().isNotEmpty()) {
                        search = it.text().toString()
                        articleViewModel.getArticles(
                            it.text().toString(),
                            context!!.getString(R.string.api_key)
                        )
                    }
                }
        )

    }

    private fun retry() {
        articleViewModel.getArticles(search, context!!.getString(R.string.api_key))
    }

    private fun showTopHeadlines(newsViewItem: NewsViewItem) {
        progress.visibility = newsViewItem.progressVisibility
        error.visibility = newsViewItem.errorVisibility
        error.text = newsViewItem.errorMessage
        rvArticles.visibility = newsViewItem.articlesVisibility
        btnRetry.visibility = newsViewItem.retryVisibility

        articleAdapter.setModels(newsViewItem.articles!!)

        if (newsViewItem.errorIncludeData) {
            showSnack(newsViewItem.errorMessage, newsViewItem.errorActionMessage, View.OnClickListener {
                if (newsViewItem.shouldRetry)
                    articleViewModel.getArticles(search, context!!.getString(R.string.api_key))
            }, Snackbar.LENGTH_LONG)
        }

    }

}