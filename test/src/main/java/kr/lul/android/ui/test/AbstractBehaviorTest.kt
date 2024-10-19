package kr.lul.android.ui.test

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult

/**
 * GIVEN - WHEN - THEN 구조의 테스트를 작성하기 위한 추상 클래스.
 */
abstract class AbstractBehaviorTest(
    body: BehaviorSpec.() -> Unit = {}
) : BehaviorSpec(body) {
    protected val logger = KotlinLogging.logger(this::class.qualifiedName!!)

    override fun isolationMode() = IsolationMode.InstancePerLeaf

    init {
        coroutineTestScope = true

        @Suppress("LeakingThis")
        beforeTest {
            if (it.descriptor.isRootTest()) {
                setUp(it)
            }
        }

        @Suppress("LeakingThis")
        afterTest {
            if (it.a.descriptor.isRootTest()) {
                tearDown(it.a, it.b)
            }
        }
    }

    /**
     * 테스트가 시작되기 전에 호출된다.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    protected open suspend fun setUp(testCase: TestCase) {
    }

    /**
     * 테스트가 종료된 후에 호출된다.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    protected open suspend fun tearDown(testCase: TestCase, testResult: TestResult) {
    }
}