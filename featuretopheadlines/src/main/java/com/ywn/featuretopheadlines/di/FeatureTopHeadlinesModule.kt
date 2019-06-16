package com.ywn.featuretopheadlines.di

import androidx.lifecycle.ViewModel
import com.ywn.common.di.viewmodel.ViewModelKey
import com.ywn.featuretopheadlines.ui.TopHeadlinesFragment
import com.ywn.featuretopheadlines.ui.TopHeadlinesWithSourceFragment
import com.ywn.featuretopheadlines.viewmodel.TopHeadlinesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FeatureTopHeadlinesModule{
    @ContributesAndroidInjector
    abstract fun topHeadlinesFragment(): TopHeadlinesFragment

    @ContributesAndroidInjector
    abstract fun topHeadlinesFragmentWithSource(): TopHeadlinesWithSourceFragment

    @Binds
    @IntoMap
    @ViewModelKey(TopHeadlinesViewModel::class)
    abstract fun topHeadlinesViewModel(topHeadlinesViewModel: TopHeadlinesViewModel): ViewModel
}