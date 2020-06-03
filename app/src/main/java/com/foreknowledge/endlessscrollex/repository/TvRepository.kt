package com.foreknowledge.endlessscrollex.repository

import com.foreknowledge.endlessscrollex.network.TmdbService
import com.foreknowledge.endlessscrollex.network.TvResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Create by Yeji on 30,May,2020.
 */
object TvRepository {
    private const val TAG = "TvRepository"

    fun getPopularTvShow(
        success:(response: TvResponse?) -> Unit,
        failure:(String, String) -> Unit,
        page: Int? = null
    ) {
        TmdbService.service.getPopularTvShows(page)
            .enqueue(object: Callback<TvResponse> {
                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    failure(TAG, "throwable: ${t.message}")
                }

                override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                    if (response.isSuccessful) {
                        success(response.body())
                    }
                    else {
                        failure(TAG, "request failure: ${response.message()}")
                    }
                }
            })
    }
}