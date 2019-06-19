package com.ian.app.muviepedia.ui.fragment.movie.slidermovieadapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.ian.app.helper.util.inflates
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.startActivity
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.ui.activity.detail.DetailActivity
import com.ian.app.muviepedia.util.MovieConstant
import com.ian.app.muviepedia.util.MovieConstant.intentToDetail
import kotlinx.android.synthetic.main.item_movie_slider.view.*

/**
 *
Created by Ian Damping on 16/04/2019.
Github = https://github.com/iandamping
 */
class SliderMovieItemAdapter(private val data: List<MovieData>, private val ctx: Context?) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val views = container.inflates(R.layout.item_movie_slider)
        if (ctx != null) {
            views.ivSliderImage.loadWithGlide(MovieConstant.imageFormatter + data[position].poster_path)
        }
        views.ivSliderImage?.setOnClickListener {

            ctx?.startActivity<DetailActivity> {
                putExtra(intentToDetail, this@SliderMovieItemAdapter.data[position].id)
            }
        }
        container.addView(views)
        return views
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = data.size
}