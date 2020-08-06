package com.foreknowledge.endlessscrollex.listener

/**
 * Created by Yeji on 03,June,2020.
 */
interface PagingListener {
    fun onSuccess()
    fun onError(tag: String, msg: String)
}