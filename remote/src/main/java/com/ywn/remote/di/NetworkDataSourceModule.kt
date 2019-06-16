package com.ywn.remote.di

import com.ywn.data.datasource.new_article_everything.ArticleEverythingDataSource
import com.ywn.data.datasource.top_headlines.TopHeadlinesDataSource
import com.ywn.remote.datasource.new_article_everything.ArticleEverythingDataSourceImpl
import com.ywn.remote.datasource.top_headlines.TopHeadlinesDataSourceImpl
import dagger.Binds
import dagger.Module

@Module(includes = [RetrofitModule::class])
abstract class NetworkDataSourceModule {

    @Binds
    abstract fun topHeadlinesNetworkDataSource(topHeadlinesDataSourceImpl: TopHeadlinesDataSourceImpl): TopHeadlinesDataSource

    @Binds
    abstract fun articleNetworkDataSource(articleEverythingDataSourceImpl: ArticleEverythingDataSourceImpl): ArticleEverythingDataSource


}