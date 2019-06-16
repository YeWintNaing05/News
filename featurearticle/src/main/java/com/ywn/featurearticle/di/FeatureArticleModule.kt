package com.ywn.featurearticle.di

import androidx.lifecycle.ViewModel
import com.ywn.common.di.viewmodel.ViewModelKey
import com.ywn.featurearticle.ui.ArticleFragment
import com.ywn.featurearticle.viewmodel.ArticleViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FeatureArticleModule {
    @ContributesAndroidInjector
    abstract fun articlesFragment(): ArticleFragment


    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    abstract fun articlesViewModel(articlesViewModel: ArticleViewModel): ViewModel
}