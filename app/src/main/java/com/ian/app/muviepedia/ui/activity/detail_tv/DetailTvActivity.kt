package com.ian.app.muviepedia.ui.activity.detail_tv

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.ian.app.helper.util.*
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.data.model.DetailTvData
import com.ian.app.muviepedia.data.model.TvData
import com.ian.app.muviepedia.util.MovieConstant
import com.ian.app.muviepedia.util.MovieConstant.intentToTvDetail
import com.ian.recyclerviewhelper.helper.setUpVertical
import kotlinx.android.synthetic.main.activity_tv_detail.*
import kotlinx.android.synthetic.main.item_similar_movie.view.*
import org.koin.android.scope.currentScope

/**
 *
Created by Ian Damping on 19/06/2019.
Github = https://github.com/iandamping
 */
class DetailTvActivity : AppCompatActivity(), DetailTvView {
    //    private val vm: GetDetailTvViewModel by viewModel()
    private val presenter: DetailTvPresenter by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setContentView(R.layout.activity_tv_detail)
        presenter.attachView(this@DetailTvActivity, this@DetailTvActivity)
        presenter.onCreate()
        presenter.getData(intent?.getIntExtra(intentToTvDetail, 0))
    }

    override fun onSuccessGetData(data: Pair<DetailTvData?, List<TvData>>) {
        tvDetailTittlesTv.text = data.first?.original_name
        tvDetailTaglinesTv.text = data.first?.original_name
        tvDetailReleaseDateTv.text = data.first?.first_air_date
        tvDetailOverviewTv.text = data.first?.overview

        ivDetailTvImages.loadWithGlide(MovieConstant.imageFormatter + data.first?.poster_path)
        ivDetailTvImages.setOnClickListener {
            fullScreen(MovieConstant.imageFormatter + data.first?.poster_path)
        }
        data.first?.genres?.forEach {
            tvDetailGenresTv.append(it.name + ", ")
        }
        data.first?.production_companies?.forEach {
            tvDetailProductionCompaniesTv.append(it.name + ", ")
        }

        appbarDetailTvLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, i ->
            var isShow = true
            var scrollRange: Int = -1

            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }
            if (scrollRange + i == 0) {
                collapsingTvToolbar.title = data.first?.original_name
                tvDetailTittlesTv.visibility = View.GONE
                isShow = true
            } else if (isShow) {
                collapsingTvToolbar.title = " "
                tvDetailTittlesTv.visibility = View.VISIBLE
                isShow = false
            }
        })

        rvSimilarTv.setUpVertical(data.second, R.layout.item_similar_movie, {
            ivSimilarMovie.loadResizeWithGlide(MovieConstant.imageFormatter + it.poster_path)
            tvSimilarMovieTittle.text = it.original_name
            tvSimilarMovieReleaseDate.text = it.first_air_date
        }, {
            startActivity<DetailTvActivity> {
                putExtra(intentToTvDetail, id)
            }
        })
    }

    override fun isAlreadyLoggedin(data: Boolean) {
    }

    override fun initView() {
    }

    override fun onFailedGetData(msg: String?) {
    }
}