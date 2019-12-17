package com.robertsproats.marsrobots.presentation.utils

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso
import timber.log.Timber

object ImageLoader {

    // encapsulate Picasso for possible replacement.
    fun loadImage(context: Context, path: String, image: ImageView) {
        if (path.isNotEmpty()) {
            try {
                Picasso.with(context).load(path).into(image)
            } catch (e: Exception) {
                Timber.e("++++ error in picasso: " + e.message)
            }
        }
    }

    fun prefectchImage(context: Context, path: String) {
        if (path.isNotEmpty()) {
            try {
                Picasso.with(context).load(path)
            } catch (e: Exception) {
                Timber.e("++++ error in picasso: " + e.message)
            }
        }
    }

}