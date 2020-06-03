package com.foreknowledge.endlessscrollex.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.foreknowledge.endlessscrollex.network.TvShow
import com.foreknowledge.endlessscrollex.repository.TvRepository

/**
 * Create by Yeji on 29,May,2020.
 */
class MainViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _tvShowList = MutableLiveData<PagedList<TvShow>>()
    val tvShowList: LiveData<PagedList<TvShow>>
        get() = _tvShowList

    init {
        loadTvShow()
    }

    private fun loadTvShow() {
        TvRepository.getPopularTvShow(
            success = {
                _isLoading.value = false
            },
            failure = { tag, message -> Log.e(tag, message) }
        )
    }
}