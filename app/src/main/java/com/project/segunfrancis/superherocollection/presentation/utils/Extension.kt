package com.project.segunfrancis.superherocollection.presentation.utils

import android.widget.ImageView
import coil.load
import com.project.segunfrancis.superherocollection.R

/**
 * Created by SegunFrancis
 */

fun ImageView.loadImage(url: String?) {
    load(url) {
        placeholder(R.drawable.loading_animation)
        error(R.drawable.ic_broken_image)
    }
}