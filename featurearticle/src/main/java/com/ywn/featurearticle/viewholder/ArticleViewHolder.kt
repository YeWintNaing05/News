package com.ywn.featurearticle.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.ywn.common.base.recycler.BaseViewHolder
import com.ywn.common.model.ArticleViewItem
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleViewHolder constructor(private val view: View) : BaseViewHolder<ArticleViewItem>(view) {


    override fun bind(data: ArticleViewItem) {
        view.txtNewsTitle.text = data.title
        view.txtNewsDescription.text = data.description


        Glide.with(view.imgNews).load(data.urlToImage).into(view.imgNews)
    }
}