package com.ian.app.muviepedia.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.constructors
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test
import java.util.Locale

class ConstructorTest {

    @Test
    fun `every constructor parameter has name derived from parameter type`() {
        Konsist
            .scopeFromProject()
            .classes()
            .constructors
            .parameters
            .assertTrue {
                val nameTitleCase = it.name.replaceFirstChar { char -> char.titlecase(Locale.getDefault()) }
                nameTitleCase == it.type.sourceType
            }
    }

    @Test
    fun `every class constructor has alphabetically ordered parameters`() {
        Konsist
            .scopeFromProject()
            .classes()
            .constructors
            .assertTrue {
                val names = it.parameters.map { parameter -> parameter.name }
                val sortedNames = names.sorted()
                names == sortedNames
            }
    }
}