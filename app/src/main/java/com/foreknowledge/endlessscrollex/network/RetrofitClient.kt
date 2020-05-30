package com.foreknowledge.endlessscrollex.network

import com.foreknowledge.endlessscrollex.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create by Yeji on 30,May,2020.
 */
object RetrofitClient {
    val service: TvShowService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvShowService::class.java)
    }
}