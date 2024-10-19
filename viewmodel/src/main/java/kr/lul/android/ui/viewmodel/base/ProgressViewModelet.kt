package kr.lul.android.ui.viewmodel.base

import kotlinx.coroutines.flow.StateFlow
import kr.lul.android.ui.state.ProgressState

/**
 * [BaseViewModel]의 로딩 등 진행 상태 관리를 분리해서 관리한다.
 */
interface ProgressViewModelet {
    /**
     * 현재 유효한 진행 상태. 진행중이 아닐 경우엔 비어있다.
     */
    val state: StateFlow<Set<ProgressState>>

    /**
     * 진행중인 경우엔 `true`.
     */
    val inProgress: Boolean
        get() = state.value.isNotEmpty()

    /**
     * 진행중 표시를 시작한다.
     *
     * @param key 진행 작업의 키. [end]의 `key`와 같은 값을 사용해야 한다.
     * @param state 진행 작업의 종류.
     *
     * @see end
     */
    fun start(key: Any, state: ProgressState)

    /**
     * 진행중 표시를 끝낸다.
     *
     * @param key 진행 작업의 키. [start]의 `key`와 같은 값을 사용해야 한다.
     */
    fun end(key: Any)
}