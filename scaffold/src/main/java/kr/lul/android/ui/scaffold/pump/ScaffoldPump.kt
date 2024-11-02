package kr.lul.android.ui.scaffold.pump

import kotlinx.coroutines.flow.StateFlow
import kr.lul.android.ui.scaffold.state.SnackbarState
import kr.lul.android.ui.scaffold.state.bottom.BottomState
import kr.lul.android.ui.scaffold.state.fab.FabPosition
import kr.lul.android.ui.scaffold.state.fab.FabState
import kr.lul.android.ui.scaffold.state.top.TopState
import kr.lul.android.ui.viewmodel.base.ProgressPump

/**
 * [kr.lul.android.ui.scaffold.compose.Scaffold]의 상태를 관리하는 펌프.
 *
 * @see kr.lul.android.ui.scaffold.compose.Scaffold
 * @see kr.lul.android.ui.scaffold.viewmodel.ScaffoldViewModel
 * @see kr.lul.android.ui.scaffold.viewmodel.ScaffoldContentViewModel
 */
interface ScaffoldPump : ProgressPump {
    /**
     * 상단 바 상태.
     */
    val top: StateFlow<TopState>

    /**
     * 하단 바 상태.
     */
    val bottom: StateFlow<BottomState>

    /**
     * 스낵바 상태.
     */
    val snackbar: StateFlow<SnackbarState>

    /**
     * 플로팅 액션 버튼 상태.
     */
    val fab: StateFlow<FabState>

    /**
     * 플로팅 액션 버튼 위치.
     */
    val fabPosition: StateFlow<FabPosition>

    /**
     * 상태를 갱신한다.
     *
     * @param top 상단 바 상태. `null`이면 갱신하지 않는다.
     * @param bottom 하단 바 상태. `null`이면 갱신하지 않는다.
     * @param snackbar 스낵바 상태. `null`이면 갱신하지 않는다.
     * @param fab 플로팅 액션 버튼 상태. `null`이면 갱신하지 않는다.
     * @param fabPosition 플로팅 액션 버튼 위치. `null`이면 갱신하지 않는다.
     */
    fun pump(
        top: TopState? = null,
        bottom: BottomState? = null,
        snackbar: SnackbarState? = null,
        fab: FabState? = null,
        fabPosition: FabPosition? = null
    )
}