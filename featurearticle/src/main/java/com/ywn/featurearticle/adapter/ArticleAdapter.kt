package com.ywn.featurearticle.adapter

import android.view.View
import com.ywn.common.base.recycler.BaseRecyclerViewAdapter
import com.ywn.common.model.ArticleViewItem
import com.ywn.featurearticle.R
import com.ywn.featurearticle.viewholder.ArticleViewHolder


class ArticleAdapter : BaseRecyclerViewAdapter<ArticleViewItem, ArticleViewHolder>() {
    override val itemLayoutResource: Int
        get() = R.layout.item_article

    override fun getViewHolder(view: View): ArticleViewHolder {
        return ArticleViewHolder(view)
    }
}