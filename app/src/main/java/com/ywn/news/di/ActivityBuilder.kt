package com.ywn.news.di

import com.ywn.news.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
/*
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel*/
}