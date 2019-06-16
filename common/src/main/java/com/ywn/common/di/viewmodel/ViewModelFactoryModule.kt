package com.ywn.common.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

  @Binds
  internal abstract fun bindProviderFactory(providerFactory: ModelProviderFactory): ViewModelProvider.Factory

}

