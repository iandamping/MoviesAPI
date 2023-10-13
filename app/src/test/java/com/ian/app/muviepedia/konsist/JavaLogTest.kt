package com.ian.app.muviepedia.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertFalse
import org.junit.Test

class JavaLogTest {

    @Test
    fun `no class should use Java util logging`() {
        Konsist
            .scopeFromProject()
            .files
            .assertFalse { it.hasImport { import -> import.name == "java.util.logging.." } }
    }
}