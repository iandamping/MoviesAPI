package com.ian.app.muviepedia.feature.detail.epoxy.companies

import coil.load
import com.ian.app.muviepedia.R
import com.ian.app.muviepedia.databinding.ItemDetailCompaniesBinding
import com.ian.app.muviepedia.feature.detail.epoxy.data.EpoxyDetailCompanyData
import com.ian.app.muviepedia.util.epoxy.ViewBindingEpoxyModelWithHolder

class EpoxyDetailCompanyContent(
    private val data: EpoxyDetailCompanyData.CompanyData,
) :
    ViewBindingEpoxyModelWithHolder<ItemDetailCompaniesBinding>() {
    override fun ItemDetailCompaniesBinding.bind() {
        ivDetailCompany.load(data.logoPath)
        tvDetailCompanyCountry.text = data.originCountry
        tvDetailCompanyName.text = data.name
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_detail_companies
    }
}
