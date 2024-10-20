package kr.lul.android.ui.viewmodel.base

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kr.lul.android.ui.state.ProgressState
import java.util.concurrent.ConcurrentHashMap

/**
 * 가장 높은 우선순위를 가진 진행 상태를 기준으로 판단한다.
 * 따라서 0개 혹은 1개의 로딩 상태를 가진다.
 */
class PriorityProgressViewModelet : ProgressViewModelet {
    companion object {
        private const val TAG = "PriorityProgressViewModelet"
    }

    private val statesLock = Any()
    private val states = ConcurrentHashMap<Any, ProgressState>()

    private val _state = MutableStateFlow(emptySet<ProgressState>())
    override val state: StateFlow<Set<ProgressState>> = _state

    private fun update() {
        _state.update {
            states.values.let { current ->
                if (current.isEmpty()) {
                    emptySet()
                } else {
                    setOf(current.minOf { it })
                }
            }
        }
        Log.v(TAG, "#update complete : states=$states")
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

    @OptIn(ExperimentalStdlibApi::class)
    override fun toString() = listOf(
        "states=$states",
        "state=${state.value}",
        "inProgress=$inProgress"
    ).joinToString(", ", "$TAG@${hashCode().toHexString(HexFormat.Default)}(", ")")
}