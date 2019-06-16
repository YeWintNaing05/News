package com.ywn.news.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.ywn.common.UIThread
import com.ywn.common.di.viewmodel.ModelProviderFactory
import com.ywn.common.di.viewmodel.ViewModelFactoryModule
import com.ywn.data.executor.JobExecutor
import com.ywn.domain.executor.PostExecutionThread
import com.ywn.domain.executor.ThreadExecutor
import com.ywn.featuretopheadlines.di.FeatureTopHeadlinesModule
import com.ywn.news.NewsApp
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [FeatureTopHeadlinesModule::class, ViewModelFactoryModule::class, AppModule.Provider::class
    ]
)
abstract class AppModule {

    @Binds
    internal abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @Binds
    internal abstract fun provideThreadExecutor(jobsExecutor: JobExecutor): ThreadExecutor


    @Module
    object Provider {
        @Provides
        @JvmStatic
        @Singleton
        fun context(application: NewsApp): Context {
            return application
        }


    }

}
