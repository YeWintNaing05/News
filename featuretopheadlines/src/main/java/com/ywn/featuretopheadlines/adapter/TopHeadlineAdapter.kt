package com.ywn.featuretopheadlines.adapter

import android.view.View
import com.ywn.common.base.recycler.BaseRecyclerViewAdapter
import com.ywn.common.model.ArticleViewItem
import com.ywn.featuretopheadlines.R
import com.ywn.featuretopheadlines.viewholder.TopHeadlineViewHolder

class TopHeadlineAdapter : BaseRecyclerViewAdapter<ArticleViewItem, TopHeadlineViewHolder>() {
    override val itemLayoutResource: Int
        get() = R.layout.item_news

    override fun getViewHolder(view: View): TopHeadlineViewHolder {
        return TopHeadlineViewHolder(view)
    }
}