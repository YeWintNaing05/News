package com.ywn.remote.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ywn.remote.BuildConfig
import com.ywn.remote.network.NewsApi
import com.ywn.remote.network.NullToEmptyStringAdapter
import com.ywn.remote.network.interceptor.ResponseInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {


    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()

        val loggerInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            loggerInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            loggerInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        builder
            .addInterceptor(ResponseInterceptor())
            .addInterceptor(loggerInterceptor)

        return builder.build()
    }

    @Provides
    @Singleton
    fun moshi(): Moshi {
        return Moshi.Builder()
            .add(NullToEmptyStringAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun retrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()

        builder.baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))

        return builder.build()
    }

    @Provides
    @Singleton
    fun service(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

}