package com.project.segunfrancis.superherocollection.presentation.utils

import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.superherocollection.presentation.main.MainActivityViewModel
import androidx.appcompat.widget.Toolbar

/**
 * Created by SegunFrancis
 *
 * Extension function to calculate the scroll position of the [RecyclerView] and
 * elevate the [Toolbar] as needed
 */
fun RecyclerView.computeScrollPosition(viewModel: MainActivityViewModel) {
    var overallYScroll = 0
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            overallYScroll += dy
            viewModel.setScrollYPosition(overallYScroll)
        }
    })
}