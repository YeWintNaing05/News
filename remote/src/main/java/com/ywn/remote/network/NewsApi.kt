package com.ywn.remote.network

import com.ywn.remote.entity.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    /**
     * search top and breaking news headlines
     */
    @GET(APIConstants.TOP_HEADLINES)
    fun getTopNewsHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>

    /**
     * search headlines with specific source
     * example : source = bbc-news
     */
    @GET(APIConstants.TOP_HEADLINES)
    fun getHeadlinesWithSpecificSource(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>

    /**
     * search new articles  with specific topic and keyword
     */
    @GET(APIConstants.NEW_ARTICLE_EVERYTHING)
    fun getNewArticlesWithSpecificTopic(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}