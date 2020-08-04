package com.project.segunfrancis.superherocollection.presesntation.utils

import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.superherocollection.presesntation.main.MainActivityViewModel
import androidx.appcompat.widget.Toolbar
import com.project.segunfrancis.customprogress.ArcProgress
import kotlinx.coroutines.delay

/**
 * Created by SegunFrancis
 */
object ExtensionFunctions {

    /**
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

    /**
     * Extension function to animate the power stats of super heroes
     */
    suspend fun ArcProgress.testFun(endValue: Float) {
        var startValue = 0.0F
        while (startValue <= endValue) {
            this.progress = startValue
            this.text = startValue.toString()
            startValue++
            if (endValue < 50)
                delay(50)
            else
                delay(15)
        }
    }
}