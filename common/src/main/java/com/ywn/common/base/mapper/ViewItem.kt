package com.ywn.common.base.mapper

interface ViewItem {
    @Visibility
    val progressVisibility: Int

    @Visibility
    val errorVisibility: Int

    val errorIncludeData: Boolean

    @Visibility
    val retryVisibility: Int


    val shouldRetry: Boolean

    val errorActionMessage: String

    val errorMessage: String
}
