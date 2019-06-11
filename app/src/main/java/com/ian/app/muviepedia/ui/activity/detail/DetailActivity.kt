package com.ian.app.muviepedia.ui.activity.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import com.ian.app.helper.util.*
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.data.local_data.LocalMovieData
import com.ian.app.muviepedia.data.model.DetailMovieData
import com.ian.app.muviepedia.data.model.MovieData
import com.ian.app.muviepedia.data.model.toDatabaseModel
import com.ian.app.muviepedia.data.viewmodel.GetDetalMovieViewModel
import com.ian.app.muviepedia.data.viewmodel.GetLocalDataViewModel
import com.ian.app.muviepedia.ui.activity.MainActivity
import com.ian.app.muviepedia.util.MovieConstant.imageFormatter
import com.ian.app.muviepedia.util.MovieConstant.intentToDetail
import com.ian.app.muviepedia.util.MovieConstant.switchBackToMain
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
    private var isLoggedIn: Boolean = false
    private var movieDataToSave: DetailMovieData? = null
    private var idForDeleteItem: Int? = null
    private var isFavorite: Boolean = false
    private var movieLocalData: MutableList<LocalMovieData> = mutableListOf()
    private var menuItem: Menu? = null
    private val vm: GetDetalMovieViewModel by viewModel()
    private val vmLocal: GetLocalDataViewModel by viewModel()
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreenAnimation()
        setContentView(R.layout.activity_detail)
        presenter = DetailPresenter(vm, vmLocal).apply {
            attachView(this@DetailActivity, this@DetailActivity)
            onCreate()
            getData(intent.getIntExtra(intentToDetail, 0))
        }
    }

    override fun isAlreadyLoggedin(data: Boolean) {
        this.isLoggedIn = data
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_saving_data_menu, menu)
        menuItem = menu

        return true
    }

    override fun onSuccessGetData(data: Pair<DetailMovieData?, List<MovieData>>) {
        this.movieLocalData.forEach {
            if (it.id == data.first?.id) {
                idForDeleteItem = it.localID
                isFavorite = true
                setFavoriteState()
            }
        }
        this.movieDataToSave = data.first
        tvDetailTittles.text = data.first?.original_title
        tvDetailTaglines.text = data.first?.tagline
        tvDetailReleaseDate.text = data.first?.release_date
        tvDetailOverview.text = data.first?.overview
        tvDetailBudget.text = data.first?.budget.toString()
        tvDetailRevenue.text = data.first?.revenue
        tvDetailMovieVote.text = data.first?.vote_average.toString()
        tvDetailMovieRuntime.text = data.first?.runtime.toString() + " Minutes"
        ivDetailMovieImages.loadWithGlide(imageFormatter + data.first?.poster_path)
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
                scrollRange = appBarLayout.totalScrollRange
            }
            if (scrollRange + i == 0) {
                collapsingToolbar.title = data.first?.original_title
                tvDetailTittles.visibility = View.GONE
                isShow = true
            } else if (isShow) {
                collapsingToolbar.title = " "
                tvDetailTittles.visibility = View.VISIBLE
                isShow = false
            }
        })

        rvSimilarMovie.setUpVertical(data.second, R.layout.item_similar_movie, {
            ivSimilarMovie.loadResizeWithGlide(imageFormatter + it.poster_path)
            tvSimilarMovieTittle.text = it.title
            tvSimilarMovieReleaseDate.text = it.release_date
        }, {
            startActivity<DetailActivity> {
                putExtra(intentToDetail, id)
            }
        })
    }

    override fun onSuccessGetLocalData(data: List<LocalMovieData>) {
        this.movieLocalData = data.toMutableList()
    }


    private fun initToolbar() {
        setSupportActionBar(toolbars)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                startActivity<MainActivity> {
                }
                true
            }
            R.id.add_to_favorite -> {
                if (isLoggedIn) {
                    if (isFavorite) {
                        presenter.deleteLocalID(idForDeleteItem)
                        isFavorite = false
                        setFavoriteState()
                    } else {
                        presenter.saveLocalData(movieDataToSave?.toDatabaseModel())
                        isFavorite = true
                        setFavoriteState()
                    }
                } else {
                    alertHelperFailed<MainActivity>("Please Login First") {
                        putExtra(switchBackToMain, "3")
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavoriteState() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmarked)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_unbookmark)
        }
    }


    override fun onFailedGetData(msg: String?) {
    }

    override fun initView() {
        initToolbar()
    }
}