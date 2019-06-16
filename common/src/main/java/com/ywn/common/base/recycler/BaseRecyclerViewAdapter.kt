package com.ywn.common.base.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.ArrayList

abstract class BaseRecyclerViewAdapter<T, V : BaseViewHolder<T>> : RecyclerView.Adapter<V>() {
    private var models: List<T>? = null
    private val modelSubject: PublishSubject<T>
    private val adapterPosition: PublishSubject<Int>

    @get:LayoutRes
    abstract val itemLayoutResource: Int

    override fun getItemCount(): Int = models!!.size

    init {
        models = ArrayList()
        modelSubject = PublishSubject.create()
        adapterPosition = PublishSubject.create()
    }

    abstract fun getViewHolder(view: View): V

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): V {
        val view = LayoutInflater.from(parent.context).inflate(itemLayoutResource, parent, false)
        val viewHolder = getViewHolder(view)

        view.setOnClickListener {
            if (viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                modelSubject.onNext(models!![viewHolder.adapterPosition])
                adapterPosition.onNext(
                    viewHolder.adapterPosition
                )
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(@NonNull holder: V, position: Int) {
        holder.bind(models!![position])
    }

    fun itemClickStream(): Observable<T> {
        return modelSubject
    }

    fun itemPositonClickStream(): Observable<Int> {
        return adapterPosition
    }

    fun setModels(models: List<T>) {
        this.models = models
        notifyDataSetChanged()
    }

}