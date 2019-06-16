package com.ywn.news

import android.app.Activity
import android.app.Application
import androidx.multidex.MultiDexApplication
import com.ywn.news.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NewsApp : MultiDexApplication(), HasActivityInjector {
    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

    }
}