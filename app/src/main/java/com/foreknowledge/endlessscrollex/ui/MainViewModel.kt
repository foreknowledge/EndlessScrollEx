package com.foreknowledge.endlessscrollex.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.foreknowledge.endlessscrollex.R
import com.foreknowledge.endlessscrollex.listener.PagingListener
import com.foreknowledge.endlessscrollex.network.TvShow
import com.foreknowledge.endlessscrollex.model.TvRepository
import com.foreknowledge.endlessscrollex.util.StringUtil
import com.foreknowledge.endlessscrollex.util.ToastUtil

/**
 * Created by Yeji on 29,May,2020.
 */
class MainViewModel : ViewModel() {
    lateinit var tvShowList: LiveData<PagedList<TvShow>>

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        loadTvShow()
    }

    private fun loadTvShow() {
        _isLoading.value = true

        tvShowList = TvRepository.getPopularTvShows(object : PagingListener {
            override fun onSuccess() {
                _isLoading.postValue(false)
            }

            override fun onError(tag: String, msg: String) {
                _isLoading.value = false
                ToastUtil.showToast(StringUtil.getString(R.string.load_fail))
                Log.e(tag, msg)
            }
        })
    }
}