package com.ian.app.muviepedia.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class FilesTest {

    @Test
    fun `files in 'ext' package must have name ending with 'Ext'`() {
        Konsist
            .scopeFromProject()
            .files
            .withPackage("..ext..")
            .assertTrue { it.hasNameEndingWith("Ext") }
    }
}