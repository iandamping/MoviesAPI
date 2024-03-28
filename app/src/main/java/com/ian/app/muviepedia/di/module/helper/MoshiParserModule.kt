package com.ian.app.muviepedia.di.module.helper

import com.ian.app.muviepedia.util.parser.MoshiParser
import com.ian.app.muviepedia.util.parser.MoshiParserImpl
import dagger.Binds
import dagger.Module

@Module
interface MoshiParserModule {

    @Binds
    fun bindsMoshiParser(impl: MoshiParserImpl):MoshiParser
}