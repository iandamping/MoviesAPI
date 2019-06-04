package com.ian.app.moviesapi.ui.activity.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.ian.app.helper.util.fullScreenAnimation
import com.ian.app.helper.util.loadResizeWithGlide
import com.ian.app.helper.util.loadWithGlide
import com.ian.app.helper.util.startActivity
import com.ian.app.moviesapi.R
import com.ian.app.moviesapi.data.model.DetailMovieData
import com.ian.app.moviesapi.data.model.MovieData
import com.ian.app.moviesapi.data.viewmodel.GetDetalMovieViewModel
import com.ian.app.moviesapi.util.MovieConstant.imageFormatter
import com.ian.app.moviesapi.util.MovieConstant.intentToDetail
import com.ian.recyclerviewhelper.helper.setUpVertical
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_similar_movie.view.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 *
Created by Ian Damping on 04/06/2019.
Github = https://github.com/iandamping
 */
class DetailActivity : AppCompatActivity(), DetailView {

    private val vm: GetDetalMovieViewModel by viewModel()
    private lateinit var presenter: DetailPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setContentView(R.layout.activity_detail)
        presenter = DetailPresenter(vm).apply {
            attachView(this@DetailActivity, this@DetailActivity)
            onCreate()
            getData(intent.getIntExtra(intentToDetail, 0))
        }
    }

    override fun onSuccessGetDetailData(data: DetailMovieData?) {
        tvDetailTittles.text = data?.original_title
        tvDetailTaglines.text = data?.tagline
        tvDetailReleaseDate.text = data?.release_date
        tvDetailOverview.text = data?.overview
        tvDetailBudget.text = data?.budget.toString()
        tvDetailRevenue.text = data?.revenue
        tvDetailMovieVote.text = data?.vote_average.toString()
        tvDetailMovieRuntime.text = data?.runtime.toString() + " Minutes"
        ivDetailMovieImages.loadWithGlide(imageFormatter + data?.backdrop_path, this)
        data?.genres?.forEach {
            tvDetailGenres.append(it.name + ", ")
        }
        data?.production_companies?.forEach {
            tvDetailProductionCompanies.append(it.name + ", ")
        }

        appbarDetailLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, i ->
            var isShow = true
            var scrollRange: Int = -1

            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange;
            }
            if (scrollRange + i == 0) {
                collapsingToolbar.title = data?.original_title
                tvDetailTittles.visibility = View.GONE
                isShow = true;
            } else if (isShow) {
                collapsingToolbar.title = " "
                tvDetailTittles.visibility = View.VISIBLE
                isShow = false;
            }
        })
    }

    override fun onSuccessGetSimilarData(data: List<MovieData>?) {
        rvSimilarMovie.setUpVertical(data, R.layout.item_similar_movie, {
            ivSimilarMovie.loadResizeWithGlide(imageFormatter + it.poster_path, this@DetailActivity)
            tvSimilarMovieTittle.text = it.title
            tvSimilarMovieReleaseDate.text = it.release_date
        }, {
            startActivity<DetailActivity> {
                putExtra(intentToDetail, id)
            }
        })
    }

    override fun onFailedGetData(msg: String?) {
    }

    override fun initView() {
    }
}