package kr.lul.android.ui.viewmodel.base

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kr.lul.android.ui.state.ProgressState

/**
 * 가장 높은 우선순위([ProgressState.priority]가 작은)를 가진 진행 상태를 유효한 진행상태로 판단한다.
 */
open class PriorityProgressPump : ProgressPump {
    companion object {
        private const val TAG = "PriorityProgressViewModelet"
    }

    private val statesLock = Any()
    private val states = mutableMapOf<Any, ProgressState>()

    private val _progress = MutableStateFlow(emptySet<ProgressState>())
    override val progress: StateFlow<Set<ProgressState>> = _progress

    @Suppress("NOTHING_TO_INLINE")
    private inline fun update() {
        var priority = Int.MAX_VALUE
        var next = mutableSetOf<ProgressState>()

        for (state in states.values) {
            when {
                state.priority < priority -> {
                    priority = state.priority
                    next = mutableSetOf(state)
                }

                state.priority == priority ->
                    next.add(state)
            }
        }
        _progress.update { next }

        Log.v(TAG, "#update complete : states=$states, progress=${progress.value}")
    }

    override fun start(key: Any, state: ProgressState) {
        synchronized(statesLock) {
            states[key] = state
            update()
        }
    }

    override fun end(key: Any) {
        synchronized(statesLock) {
            states.remove(key)
            update()
        }
    }

    override fun toString() = listOf(
        "states=$states",
        "progress=${progress.value}"
    ).joinToString(", ", "$TAG(", ")")
}