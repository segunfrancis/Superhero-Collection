package com.project.segunfrancis.superherocollection.presesntation.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by SegunFrancis
 *
 * Specifies equal margins for [RecyclerView] items
 */

class MarginItemDecoration(
    private val numberOfColumns: Int,
    private val spaceHeight: Int,
    private val topMargin: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            when (numberOfColumns) {
                // Portrait mode
                2 -> {
                    if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(
                            view
                        ) == 1
                    ) {
                        top = topMargin + spaceHeight
                    }
                    if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(
                            view
                        ) % 2 == 0
                    ) {
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
                // Landscape mode and tablets
                3 -> {
                    if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(
                            view
                        ) == 1 || parent.getChildAdapterPosition(
                            view
                        ) == 2
                    ) {
                        top = topMargin + spaceHeight
                    }
                    if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(
                            view
                        ) % 3 == 0
                    ) {
                        /* Views that are positioned on the left */
                        right = 0
                    }
                    if (parent.getChildAdapterPosition(view) == 2 || rightPositions(
                            parent.getChildAdapterPosition(
                                view
                            )
                        )
                    ) {
                        /* Views that are positioned on the right */
                        right = spaceHeight
                    }
                    left = spaceHeight
                    bottom = spaceHeight
                }
            }
        }
    }

    /**
     *  Utility function that helps calculate the margin of the [RecyclerView] items on the right edge
     *  @param position represents the childAdapterPosition
     */
    private fun rightPositions(position: Int): Boolean {
        val tempList: ArrayList<Int> = arrayListOf()
        if (position > 2)
            for (i in 2..position step 3) {
                tempList.add(i)
            }
        return tempList.contains(position)
    }
}