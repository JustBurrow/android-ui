package kr.lul.android.ui.viewmodel.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kr.lul.android.ui.state.ProgressState
import java.util.UUID
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class TestBaseViewModel(
    tag: String = "TestBaseViewModel",
    progress: ProgressViewModelet = PriorityProgressViewModelet()
) : BaseViewModel(tag, progress) {
    fun launchBlock(
        progress: ProgressState? = null,
        onComplete: ((Throwable?) -> Unit)? = null,
        key: UUID = UUID.randomUUID(),
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(progress, onComplete, key, context, start, block)

    override fun toString() = "TestBaseViewModel(${super.toString()})"
}