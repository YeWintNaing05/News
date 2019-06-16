package com.ywn.news.di

import com.ywn.common.di.module.RepositoryModule
import com.ywn.common.di.viewmodel.ViewModelFactoryModule
import com.ywn.featurearticle.di.FeatureArticleModule
import com.ywn.featuretopheadlines.di.FeatureTopHeadlinesModule
import com.ywn.news.NewsApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        FeatureTopHeadlinesModule::class,
        FeatureArticleModule::class,
        ViewModelFactoryModule::class,
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        RepositoryModule::class]
)
interface AppComponent {

    fun inject(app: NewsApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: NewsApp): Builder

        fun build(): AppComponent
    }
}