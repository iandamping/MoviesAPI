package com.ian.app.muviepedia.konsist

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class CompanionObjectTest {

    @Test
    fun `companion object is last declaration in the class`() {
        Konsist
            .scopeFromProject()
            .classes()
            .assertTrue {
                val companionObject = it.objects(includeNested = false).lastOrNull { obj ->
                    obj.hasModifier(KoModifier.COMPANION)
                }

                if (companionObject != null) {
                    it.declarations(includeNested = false, includeLocal = false).last() == companionObject
                } else {
                    true
                }
            }
    }
}