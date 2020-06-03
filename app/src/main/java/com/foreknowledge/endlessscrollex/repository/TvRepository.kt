package com.foreknowledge.endlessscrollex.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.foreknowledge.endlessscrollex.network.TvShow

/**
 * Create by Yeji on 30,May,2020.
 */
object TvRepository {
    private const val DEFAULT_PAGE_SIZE = 20

    fun getPopularTvShows(
        onSuccess: (data: List<TvShow>) -> Unit,
        onError: (tag: String, msg: String) -> Unit,
        pageSize: Int = DEFAULT_PAGE_SIZE
    ): LiveData<PagedList<TvShow>> {
        val dataSource = TvPagedDataSource.Factory(onSuccess, onError)
        return LivePagedListBuilder(dataSource, pageSize).build()
    }
}