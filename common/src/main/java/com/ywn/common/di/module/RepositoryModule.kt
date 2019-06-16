package com.ywn.common.di.module

import com.ywn.data.repository.ArticleRepositoryImpl
import com.ywn.data.repository.TopHeadlinesRepositoryImpl
import com.ywn.domain.repository.ArticleRepository
import com.ywn.domain.repository.TopHeadlinesRepository
import com.ywn.remote.di.NetworkDataSourceModule
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkDataSourceModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun articleRepository(articleRepositoryImpl: ArticleRepositoryImpl): ArticleRepository

    @Binds
    abstract fun topHeadlinesRepository(headlinesRepositoryImpl: TopHeadlinesRepositoryImpl): TopHeadlinesRepository

}