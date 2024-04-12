package com.ian.app.muviepedia.core.presentation.epoxyMapper.principal.television

import com.ian.app.muviepedia.core.data.repository.model.Television
import com.ian.app.muviepedia.core.data.repository.model.TelevisionDetail
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevision
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailCompany
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailContent
import com.ian.app.muviepedia.core.presentation.model.EpoxyTelevisionDetailSimilarContent
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailCompanyData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailContentData
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailSimilarData
import com.ian.app.muviepedia.feature.home.epoxy.television.data.EpoxyTelevisionData
import com.ian.app.muviepedia.feature.search.television.EpoxySearchTelevisionData

interface EpoxyTelevisionMapper {

    fun extractTelevisionToEpoxy(televisionData: List<Television>): List<EpoxyTelevision>

    fun extractDetailCompanyTelevisionToEpoxy(televisionData: List<TelevisionDetail.ProductionCompany>): List<EpoxyTelevisionDetailCompany>

    fun extractDetailContentTelevisionToEpoxy(televisionData: TelevisionDetail): EpoxyTelevisionDetailContent

    fun extractDetailSimilarTelevisionToEpoxy(televisionData: List<Television>): List<EpoxyTelevisionDetailSimilarContent>

    fun epoxySearchTelevisionListMapper(data: List<EpoxyTelevision>): List<EpoxySearchTelevisionData.TelevisionData>

    fun epoxyPopularTVListMapper(data: List<EpoxyTelevision>): List<EpoxyTelevisionData.TelevisionData>

    fun epoxyTopRatedTvListMapper(data: List<EpoxyTelevision>): List<EpoxyTelevisionData.TelevisionData>

    fun epoxyDetailCompanyTelevisionListMapper(data: List<EpoxyTelevisionDetailCompany>): List<EpoxyDetailCompanyData.CompanyData>

    fun epoxyDetailContentTelevisionMapper(data: EpoxyTelevisionDetailContent): EpoxyDetailContentData.TelevisionData

    fun epoxyDetailSimilarTelevisionListMapper(data: List<EpoxyTelevisionDetailSimilarContent>): List<EpoxyDetailSimilarData.SimilarData>
}
