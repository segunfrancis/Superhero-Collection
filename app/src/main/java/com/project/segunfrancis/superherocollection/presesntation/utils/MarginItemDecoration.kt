package com.project.segunfrancis.superherocollection.presesntation.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by SegunFrancis
 *
 * Specifies equal margins for recycler view items
 */

class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) == 1) {
                top = spaceHeight
            }
            if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) % 2 == 0) {
                /* Even numbered positions, i.e. views that are positioned on the left */
                right = 0
            }
            if (parent.getChildAdapterPosition(view) % 2 != 0) {
                /* Odd numbered positions, i.e. views that are positioned on the right */
                right = spaceHeight
            }
            left = spaceHeight
            bottom = spaceHeight
        }
    }
}