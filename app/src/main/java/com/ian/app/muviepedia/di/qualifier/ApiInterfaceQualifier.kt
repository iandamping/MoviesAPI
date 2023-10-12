package com.ian.app.muviepedia.di.qualifier

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MovieApiInterfaceQualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MoviePagingApiInterfaceQualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class TelevisionApiInterfaceQualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class TelevisionPagingApiInterfaceQualifier
