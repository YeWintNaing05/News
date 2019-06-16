package com.ywn.common.base.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * created by Ye Witn Naing 5/26/2019
 */

abstract class BaseViewHolder<T>
    (itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(data: T)

}
