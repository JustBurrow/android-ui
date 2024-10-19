package kr.lul.android.ui.viewmodel.base

import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kr.lul.android.ui.state.BlockingProgressState
import kr.lul.android.ui.state.NonBlockingProgressState

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest : BehaviorSpec() {
    private val logger = KotlinLogging.logger { }

    private lateinit var viewModel: TestBaseViewModel

    init {
        this.isolationMode = IsolationMode.InstancePerLeaf
        this.coroutineTestScope = true

        beforeTest {
            if (it.descriptor.isRootTest()) {
                Dispatchers.setMain(StandardTestDispatcher())

                viewModel = TestBaseViewModel(it.name.testName)
                logger.info { "[SETUP] viewModel=$viewModel" }
            }
        }

        afterTest {
            if (it.a.descriptor.isRootTest()) {
                Dispatchers.resetMain()
            }
        }

        table(
            headers("progress"),
            row(BlockingProgressState),
            row(NonBlockingProgressState)
        ).forAll { progress ->
            given("launch($progress)") {
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
    }
}
