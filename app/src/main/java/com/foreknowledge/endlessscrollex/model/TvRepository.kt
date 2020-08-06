package com.foreknowledge.endlessscrollex.model

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.foreknowledge.endlessscrollex.listener.PagingListener
import com.foreknowledge.endlessscrollex.network.TvShow

/**
 * Created by Yeji on 30,May,2020.
 */
object TvRepository {
    private const val DEFAULT_PAGE_SIZE = 20

    fun getPopularTvShows(
        listener: PagingListener,
        pageSize: Int = DEFAULT_PAGE_SIZE
    ): LiveData<PagedList<TvShow>> {
        val dataSourceFactory = TvPagedDataSource.Factory(listener)

        val pageListConfig = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setPrefetchDistance(pageSize * 3)
            .setEnablePlaceholders(true)
            .build()

        return LivePagedListBuilder(dataSourceFactory, pageListConfig).build()
    }
}