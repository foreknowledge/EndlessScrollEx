package com.foreknowledge.endlessscrollex.util

import android.widget.Toast
import com.foreknowledge.endlessscrollex.GlobalApp

/**
 * Create by Yeji on 03,June,2020.
 */
object ToastUtil {
    fun showToast(
        msg: String,
        length: Int = Toast.LENGTH_LONG
    ) = Toast.makeText(GlobalApp.getContext(), msg, length).show()
}