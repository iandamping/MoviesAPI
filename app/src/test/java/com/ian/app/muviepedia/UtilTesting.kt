package com.ian.app.muviepedia

import com.ian.app.muviepedia.util.isExpireds
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Assert
import org.junit.Test

class UtilTesting {

    @Test
    fun isExpiredTest(){
        mockkStatic("com.ian.app.muviepedia.util.ExpiresUtilKt")
        every { isExpireds(any()) } returns false

        val state = isExpireds(1L)

        Assert.assertFalse(state)
    }
}