package com.ian.app.muviepedia.di.qualifier


import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher



@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CustomIOScope

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CustomDefaultScope


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CustomMainScope


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class CustomNonDispatcherScope

