package com.habib.testingairlift.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("eventMsg", "articles")
fun hideIfNetworkError(view: View, eventMsg: String?, articles: Any?) {
//    view.visibility = if (articles != null) View.GONE else View.VISIBLE

    eventMsg?.let {
        if(it != Constants.LOADING) {
            view.visibility = View.GONE
        }
    }
}