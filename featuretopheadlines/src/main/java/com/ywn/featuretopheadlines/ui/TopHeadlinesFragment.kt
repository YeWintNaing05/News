package com.ywn.featuretopheadlines.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.view.RxView
import com.ywn.common.base.BaseFragment
import com.ywn.common.model.NewsViewItem
import com.ywn.featuretopheadlines.R
import com.ywn.featuretopheadlines.adapter.TopHeadlineAdapter
import com.ywn.featuretopheadlines.viewmodel.TopHeadlinesViewModel
import kotlinx.android.synthetic.main.fragment_topheadlines.*
import javax.inject.Inject

class TopHeadlinesFragment : BaseFragment() {


    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var topHeadlinesViewModel: TopHeadlinesViewModel

    lateinit var topHeadlinesAdapter: TopHeadlineAdapter

    override val layoutResource: Int
        get() = R.layout.fragment_topheadlines


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        topHeadlinesViewModel = ViewModelProviders.of(this, factory).get(TopHeadlinesViewModel::class.java)


        if (savedInstanceState == null) {
            topHeadlinesViewModel.getTopHeadlines("US", context!!.getString(R.string.api_key))
        }

        topHeadlinesAdapter = TopHeadlineAdapter()

        rvTopHeadlines.apply {
            adapter = topHeadlinesAdapter
            layoutManager = LinearLayoutManager(context)
        }

        startSubscription()

    }

    private fun startSubscription() {
        add(topHeadlinesViewModel.streamTopHeadlines().subscribe(this::showTopHeadlines))
        add(RxView.clicks(btnRetry).subscribe {
            retry()
        })
    }

    private fun retry() {
        topHeadlinesViewModel.getTopHeadlines("US", context!!.getString(R.string.api_key))
    }

    private fun showTopHeadlines(newsViewItem: NewsViewItem) {
        progress.visibility = newsViewItem.progressVisibility
        error.visibility = newsViewItem.errorVisibility
        error.text = newsViewItem.errorMessage
        rvTopHeadlines.visibility = newsViewItem.articlesVisibility
        btnRetry.visibility = newsViewItem.retryVisibility

        topHeadlinesAdapter.setModels(newsViewItem.articles!!)

        if (newsViewItem.errorIncludeData) {
            showSnack(newsViewItem.errorMessage, newsViewItem.errorActionMessage, View.OnClickListener {
                if (newsViewItem.shouldRetry)
                    topHeadlinesViewModel.getTopHeadlines("US", context!!.getString(R.string.api_key))
            }, Snackbar.LENGTH_LONG)
        }

    }


}