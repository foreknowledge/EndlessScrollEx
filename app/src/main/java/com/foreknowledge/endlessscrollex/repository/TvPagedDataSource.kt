package com.foreknowledge.endlessscrollex.repository

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.foreknowledge.endlessscrollex.network.TmdbService
import com.foreknowledge.endlessscrollex.network.TvResponse
import com.foreknowledge.endlessscrollex.network.TvShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Create by Yeji on 03,June,2020.
 */
class TvPagedDataSource private constructor(
    private val onSuccess: (data: List<TvShow>) -> Unit,
    private val onError: (tag: String, msg: String) -> Unit
) : PageKeyedDataSource<Int, TvShow>() {

    class Factory(
        private val onSuccess: (data: List<TvShow>) -> Unit,
        private val onError: (tag: String, msg: String) -> Unit
    ) : DataSource.Factory<Int, TvShow>() {
        override fun create(): DataSource<Int, TvShow> =
            TvPagedDataSource(onSuccess, onError)
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShow>
    ) {
        fetchPopulars(FIRST_PAGE) { callback.onResult(it, null, FIRST_PAGE + 1) }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        val page = params.key
        fetchPopulars(page) { callback.onResult(it, page + 1) }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        val page = params.key
        fetchPopulars(page) { callback.onResult(it, page - 1) }
    }

    private fun fetchPopulars(
        page: Int,
        onResult: (response: List<TvShow>) -> Unit
    ) {
        TmdbService.service.getPopularTvShows(page)
            .enqueue(object: Callback<TvResponse> {
                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    onError(TAG, "throwable: ${t.message}")
                }

                override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.results ?: emptyList()
                        onResult(data)
                        onSuccess(data)
                    }
                    else {
                        onError(TAG, "request error: ${response.message()}")
                    }
                }
            })
    }

    companion object {
        private const val FIRST_PAGE = 1
        private const val TAG = "TvPagedDataSource"
    }
}