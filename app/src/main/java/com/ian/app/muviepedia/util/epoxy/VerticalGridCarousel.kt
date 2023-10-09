package com.ian.app.muviepedia.util.epoxy

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

// GridCarousel
@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT, fullSpan = true)
class VerticalGridCarousel(context: Context) : Carousel(context) {
    override fun createLayoutManager(): LayoutManager {
        return GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
    }
}