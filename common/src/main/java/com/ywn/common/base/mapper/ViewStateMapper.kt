package com.ywn.common.base.mapper

import android.content.Context


abstract class ViewStateMapper<V, D>(val context: Context) {

    abstract fun map(domainModel: D): V
}