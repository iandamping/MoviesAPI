package com.ian.app.muviepedia.core.domain.model

sealed class DomainSource<out T> {
    data class Success<out T>(val data: T) : DomainSource<T>()

    data class Error(val message: String) : DomainSource<Nothing>()
}
