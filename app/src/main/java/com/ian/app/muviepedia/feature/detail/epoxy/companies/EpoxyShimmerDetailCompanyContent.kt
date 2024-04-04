package com.ian.app.muviepedia.feature.detail.epoxy.companies

import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ShimmerDetailCompanyBinding
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyShimmerDetailCompanyContent :
    ViewBindingEpoxyModelWithHolder<ShimmerDetailCompanyBinding>() {
    override fun ShimmerDetailCompanyBinding.bind() {
        shimmerDetailCompany.startShimmer()
    }

    override fun ShimmerDetailCompanyBinding.unbind() {
        shimmerDetailCompany.stopShimmer()
    }

    override fun getDefaultLayout(): Int {
        return R.layout.shimmer_detail_company
    }
}
