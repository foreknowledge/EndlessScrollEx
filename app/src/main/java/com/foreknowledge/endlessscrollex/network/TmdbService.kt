package com.foreknowledge.endlessscrollex.network

import com.foreknowledge.endlessscrollex.network.TmdbApi.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Create by Yeji on 30,May,2020.
 */
object TmdbService {
    val service: TmdbApi by lazy {
        val logger = HttpLoggingInterceptor().apply {
            level =  HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApi::class.java)
    }
}