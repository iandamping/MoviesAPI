package com.ian.app.muviepedia.feature.detail.epoxy.data

sealed class EpoxyDetailCompanyData {

    data class Shimmer(val epoxyId: Int) : EpoxyDetailCompanyData()

    data class CompanyData(
        val id: Int,
        val logoPath: String,
        val name: String,
        val originCountry: String
    ) : EpoxyDetailCompanyData()

    data class Error(val errorMessage:String) : EpoxyDetailCompanyData()
}