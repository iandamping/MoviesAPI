package com.ian.app.muviepedia.feature.detail.epoxy.movie

import android.view.View
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.DetailDescriptionContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class EpoxyDetailDescriptionMovieContent(
    private val title: String,
    private val tagline: String,
    private val overview: String,
    private val voteAverage: Double,
    private val releaseDate: String,
    private val revenue: String,
) :
    ViewBindingEpoxyModelWithHolder<DetailDescriptionContentBinding>() {
    private val moneyFormat: NumberFormat = NumberFormat.getCurrencyInstance()

    override fun DetailDescriptionContentBinding.bind() {
        moneyFormat.maximumFractionDigits = 0
        moneyFormat.currency = Currency.getInstance(Locale.US)

        tvDetailTaglines.visibility =
            if (tagline.isEmpty()) View.GONE else View.VISIBLE
        tvDetailRating.text =
            root.context.getString(R.string.rating_movie, voteAverage.toString())
        tvDetailTaglines.text = tagline
        tvDetailTitle.text = title
        tvDetailDesc.text = overview
        tvDetailHint1.text = root.context.getString(R.string.release_date)
        tvDetailContent1.text = releaseDate
        tvDetailHint2.text = root.context.getString(R.string.total_revenue)
        tvDetailContent2.text =
            moneyFormat.format(revenue.toLong())
    }

    override fun getDefaultLayout(): Int {
        return R.layout.detail_description_content
    }
}