package com.foreknowledge.endlessscrollex.util

import android.content.Context
import android.widget.Toast
import com.foreknowledge.endlessscrollex.GlobalApp

/**
 * Created by Yeji on 03,June,2020.
 */
object ToastUtil {
    fun showToast(
        msg: String,
        context: Context = GlobalApp.getContext(),
        length: Int = Toast.LENGTH_LONG
    ) = Toast.makeText(context, msg, length).show()
}