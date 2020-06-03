package com.foreknowledge.endlessscrollex.network

import com.foreknowledge.endlessscrollex.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Create by Yeji on 30,May,2020.
 */
interface TmdbApi {
    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("page") page: Int? = 1,
        @Query("api_key") apiKey: String? = BuildConfig.API_KEY
    ): Call<TvResponse>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2"
    }
}