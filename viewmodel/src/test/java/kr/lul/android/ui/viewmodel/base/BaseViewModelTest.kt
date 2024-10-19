package kr.lul.android.ui.viewmodel.base

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table

class BaseViewModelTest : BehaviorSpec() {
    private val logger = KotlinLogging.logger { }

    init {
        this.isolationMode = IsolationMode.InstancePerLeaf

        beforeTest {
            if (it.descriptor.isRootTest()) {
                logger.info { "[SETUP]" }
            }
        }

        afterTest {
            if (it.a.descriptor.isRootTest()) {
                logger.info { "[TEARDOWN]" }
            }
        }

        table(
            headers("a", "b"),
            row(1, 1),
            row(2, 2),
            row(3, 3)
        ).forAll { a, b ->
            given("#given") {
                logger.info { "[GIVEN] a=$a, b=$b" }

                `when`("#when") {
                    logger.info { "[WHEN]" }

                    then("#then") {
                        logger.info { "[THEN]" }
                    }
                }
            }
        }
    }
}
