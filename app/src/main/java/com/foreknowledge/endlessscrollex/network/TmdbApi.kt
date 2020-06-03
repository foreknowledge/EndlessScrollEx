package com.foreknowledge.endlessscrollex.network

import com.foreknowledge.endlessscrollex.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Create by Yeji on 30,May,2020.
 */
interface TvShowService {
    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("page") page: Int? = 1,
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY
    ): Call<TvResponse>
}