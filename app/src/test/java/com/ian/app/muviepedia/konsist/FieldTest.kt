package com.ian.app.muviepedia.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.provider.hasAnnotationOf
import com.lemonappdev.konsist.api.verify.assertFalse
import org.junit.Test
import javax.inject.Inject

class FieldTest {

    @Test
    fun `no field should have 'm' prefix`() {
        Konsist
            .scopeFromProject()
            .classes()
            .properties()
            .assertFalse {
                val secondCharacterIsUppercase = it.name.getOrNull(1)?.isUpperCase() ?: false
                it.name.startsWith('m') && secondCharacterIsUppercase
            }
    }

    /* tidak ramah untuk dagger, mungkin ramah untuk hilt */
//    @Test
//    fun `no class should use field injection`() {
//        Konsist
//            .scopeFromProject()
//            .classes()
//            .properties()
//            .assertFalse { it.hasAnnotationOf<Inject>() }
//    }
}