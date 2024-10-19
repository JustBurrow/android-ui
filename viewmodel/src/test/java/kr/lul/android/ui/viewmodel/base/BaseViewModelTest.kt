package kr.lul.android.ui.viewmodel.base

import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kr.lul.android.ui.state.BlockingProgressState
import kr.lul.android.ui.state.NonBlockingProgressState
import kr.lul.android.ui.test.AbstractBehaviorTest

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest : AbstractBehaviorTest() {
    private lateinit var viewModel: TestBaseViewModel

    override suspend fun setUp(testCase: TestCase) {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = TestBaseViewModel(testCase.name.testName)
        logger.info { "[SETUP] viewModel=$viewModel" }
    }

    override suspend fun tearDown(testCase: TestCase, testResult: TestResult) {
        viewModel.onCleared()
        Dispatchers.resetMain()
    }

    init {
        given("launch - progress") {
            table(
                headers("progress"),
                row(BlockingProgressState),
                row(NonBlockingProgressState)
            ).forAll { progress ->
                val block: suspend CoroutineScope. () -> Unit = {
                    delay(1000)
                }
                logger.info { "[GIVEN] progress=$progress, block=$block" }

                `when`("launch를 실행하면") {
                    val job = viewModel.launchBlock(progress = progress, block = block)
                    logger.info { "[WHEN] job=$job, viewModel=$viewModel" }

                    then("block이 완료될 때 까지 진행 상태가 유지된다.") {
                        viewModel.progress.inProgress shouldBe true
                        viewModel.progress.state.value shouldContain progress

                        job.join()
                        viewModel.progress.inProgress shouldBe false
                        viewModel.progress.state.value.shouldBeEmpty()
                    }
                }
            }
        }

        given("launch - progress, onComplete") {
            table(
                headers("progress"),
                row(null),
                row(BlockingProgressState),
                row(NonBlockingProgressState)
            ).forAll { progress ->
                val onComplete: (Throwable?) -> Unit = mockk()
                every { onComplete(any()) } returns Unit
                logger.info { "[GIVEN] progress=$progress, onComplete=$onComplete" }

                `when`("launch를 실행하면") {
                    val job = viewModel.launchBlock(progress = progress, onComplete = onComplete) {
                        delay(1000)
                    }
                    logger.info { "[WHEN] job=$job, viewModel=$viewModel" }

                    then("onComplete가 1회 실행된다.") {
                        job.join()
                        verify(exactly = 1) { onComplete(null) }
                    }
                }
            }
        }
    }
}