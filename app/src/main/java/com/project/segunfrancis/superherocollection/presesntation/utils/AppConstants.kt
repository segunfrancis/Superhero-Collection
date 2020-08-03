package com.project.segunfrancis.superherocollection.presesntation.utils

import android.content.Context
import android.util.TypedValue

/**
 * Created by SegunFrancis
 */
object AppConstants {
    const val BASE_URL: String = " https://rosariopfernandes.github.io/dc-villains-api/"
    const val INTENT_KEY: String = "character_item"


    /**
     * Converts dp which is returned by the XML to pixels
     */
    fun convertDpToPx(context: Context): Int {
        val tv = TypedValue()
        var actionBarHeight = 0
        if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight =
                TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        }
        return actionBarHeight
    }
}