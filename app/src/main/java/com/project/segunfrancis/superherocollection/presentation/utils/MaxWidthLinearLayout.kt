package com.project.segunfrancis.superherocollection.presentation.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.project.segunfrancis.superherocollection.R
import kotlin.math.min

class MaxWidthLinearLayout : LinearLayout {
    private var mMaxWidth = Int.MAX_VALUE

    constructor(context: Context) : super(context) {
        init(context, null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(context, attrs, defStyle, 0)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val a = context.obtainStyledAttributes(attrs, ATTRS)
        mMaxWidth = a.getLayoutDimension(0, Int.MAX_VALUE)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthMeasureSpec = widthMeasureSpec
        val newSpecWidth = min(MeasureSpec.getSize(widthMeasureSpec), mMaxWidth)
        widthMeasureSpec =
            MeasureSpec.makeMeasureSpec(newSpecWidth, MeasureSpec.getMode(widthMeasureSpec))
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    companion object {
        private val ATTRS = intArrayOf(
            R.attr.maxWidth
        )
    }
}
