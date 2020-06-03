package com.foreknowledge.endlessscrollex.util

import androidx.annotation.StringRes
import com.foreknowledge.endlessscrollex.GlobalApp

/**
 * Create by Yeji on 03,June,2020.
 */
object StringUtil {
    fun getString(@StringRes resId: Int) = GlobalApp.getContext().getString(resId)
}