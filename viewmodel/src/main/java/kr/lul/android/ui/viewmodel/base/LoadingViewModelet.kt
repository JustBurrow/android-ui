package kr.lul.android.ui.viewmodel.base

import kotlinx.coroutines.flow.StateFlow
import kr.lul.android.ui.state.LoadingState

/**
 * [BaseViewModel]의 로딩 상태 관리를 분리, 관리한다.
 */
interface LoadingViewModelet {
    /**
     * 현재 유효한 로딩 상태. 로딩중이 아닐 경우엔 비어있다.
     */
    val state: StateFlow<Set<LoadingState>>

    /**
     * 로딩중인 경우엔 `true`.
     */
    val loading: Boolean
        get() = state.value.isNotEmpty()

    /**
     * 로딩중 표시를 시작한다.
     *
     * @param key 로딩 작업의 키. [end]의 `key`와 같은 값을 사용해야 한다.
     * @param state 로딩 작업의 종류.
     *
     * @see end
     */
    fun start(key: Any, state: LoadingState)

    /**
     * 로딩중 표시를 끝낸다.
     *
     * @param key 로딩 작업의 키. [start]의 `key`와 같은 값을 사용해야 한다.
     */
    fun end(key: Any)
}