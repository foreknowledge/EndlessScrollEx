package com.foreknowledge.endlessscrollex.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.foreknowledge.endlessscrollex.R

/**
 * Create by Yeji on 30,May,2020.
 */

@BindingAdapter("bind_image")
fun ImageView.bindImage(url: String) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.no_image)
        .into(this)
}