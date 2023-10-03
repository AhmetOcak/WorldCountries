package com.worldcountries.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        with(outRect) {
            if (position == 0 || position == 1) {
                top = spaceSize
            }
            left = spaceSize / 2
            right = spaceSize / 2
            bottom = spaceSize
        }
    }
}