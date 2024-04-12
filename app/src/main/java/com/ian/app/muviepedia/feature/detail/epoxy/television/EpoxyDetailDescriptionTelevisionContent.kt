package com.ian.app.muviepedia.feature.detail.epoxy.television

import android.view.View
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.DetailDescriptionContentBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class EpoxyDetailDescriptionTelevisionContent(
    private val title: String,
    private val tagline: String,
    private val overview: String,
    private val voteAverage: Double,
    private val firstAiringDate: String,
    private val lastAiringDate: String,
    private val numberOfEpisodes: String,
    private val numberOfSeasons: String,
) :
    ViewBindingEpoxyModelWithHolder<DetailDescriptionContentBinding>() {
    private val moneyFormat: NumberFormat = NumberFormat.getCurrencyInstance()

    override fun DetailDescriptionContentBinding.bind() {
        moneyFormat.maximumFractionDigits = 0
        moneyFormat.currency = Currency.getInstance(Locale.US)

        tvDetailTaglines.visibility =
            if (tagline.isEmpty()) View.GONE else View.VISIBLE
        tvDetailRating.text =
            root.context.getString(R.string.rating_television, voteAverage.toString())
        tvDetailTitle.text = title
        tvDetailTaglines.text = tagline
        tvDetailDesc.text = overview
        tvDetailHint1.text = root.context.getString(R.string.first_release_date)
        tvDetailContent1.text = firstAiringDate
        tvDetailHint2.text = root.context.getString(R.string.last_release_date)
        tvDetailContent2.text = lastAiringDate
        tvDetailHint3.text = root.context.getString(R.string.number_of_episodes)
        tvDetailContent3.text =
            numberOfEpisodes
        tvDetailHint4.text = root.context.getString(R.string.number_of_seasons)
        tvDetailContent4.text =
            numberOfSeasons
    }

    override fun getDefaultLayout(): Int {
        return R.layout.detail_description_content
    }
}