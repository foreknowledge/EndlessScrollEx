package com.foreknowledge.endlessscrollex.model

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.foreknowledge.endlessscrollex.listener.PagingListener
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
    private val listener: PagingListener
) : PageKeyedDataSource<Int, TvShow>() {
    private var totalPages: Int = 0

    class Factory(private val listener: PagingListener) : DataSource.Factory<Int, TvShow>() {
        override fun create(): DataSource<Int, TvShow> =
            TvPagedDataSource(listener)
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShow>
    ) {
        fetchPopulars(FIRST_PAGE) { data ->
            callback.onResult(data, null, FIRST_PAGE + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        val page = params.key
        if (page > totalPages) {
            return
        }

        fetchPopulars(page) { data ->
            callback.onResult(data, page + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        // do nothing
    }

    private fun fetchPopulars(
        page: Int,
        onResult: (response: List<TvShow>) -> Unit
    ) {
        TmdbService.service.getPopularTvShows(page)
            .enqueue(object: Callback<TvResponse> {
                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    listener.onError(TAG, "throwable: ${t.message}")
                }

                override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            totalPages = it.totalPages
                            onResult(it.results)
                        }
                        listener.onSuccess()
                    }
                    else {
                        listener.onError(TAG, "request error: ${response.message()}")
                    }
                }
            })
    }

    companion object {
        private const val FIRST_PAGE = 1
        private const val TAG = "TvPagedDataSource"
    }
}