package com.ian.app.muviepedia.util.epoxy

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

// GridCarousel
@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HorizontalGridCarousel(context: Context) : Carousel(context) {
    override fun createLayoutManager(): LayoutManager {
        return GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
    }
}
