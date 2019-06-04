package com.ian.app.moviesapi.ui.activity.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.ian.app.helper.util.*
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
    override fun onSuccessGetData(data: Pair<DetailMovieData?, List<MovieData>>) {
        tvDetailTittles.text = data.first?.original_title
        tvDetailTaglines.text = data.first?.tagline
        tvDetailReleaseDate.text = data.first?.release_date
        tvDetailOverview.text = data.first?.overview
        tvDetailBudget.text = data.first?.budget.toString()
        tvDetailRevenue.text = data.first?.revenue
        tvDetailMovieVote.text = data.first?.vote_average.toString()
        tvDetailMovieRuntime.text = data.first?.runtime.toString() + " Minutes"
        ivDetailMovieImages.loadWithGlide(imageFormatter + data.first?.poster_path, this)
        ivDetailMovieImages.setOnClickListener {
            fullScreen(imageFormatter + data.first?.poster_path)
        }
        data.first?.genres?.forEach {
            tvDetailGenres.append(it.name + ", ")
        }
        data.first?.production_companies?.forEach {
            tvDetailProductionCompanies.append(it.name + ", ")
        }

        appbarDetailLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, i ->
            var isShow = true
            var scrollRange: Int = -1

            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange;
            }
            if (scrollRange + i == 0) {
                collapsingToolbar.title = data.first?.original_title
                tvDetailTittles.visibility = View.GONE
                isShow = true;
            } else if (isShow) {
                collapsingToolbar.title = " "
                tvDetailTittles.visibility = View.VISIBLE
                isShow = false;
            }
        })

        rvSimilarMovie.setUpVertical(data.second, R.layout.item_similar_movie, {
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