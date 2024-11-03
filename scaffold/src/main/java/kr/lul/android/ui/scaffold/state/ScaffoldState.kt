package kr.lul.android.ui.scaffold.state

import androidx.compose.runtime.Immutable
import kr.lul.android.ui.scaffold.state.bottom.BottomState
import kr.lul.android.ui.scaffold.state.dev.DevState
import kr.lul.android.ui.scaffold.state.fab.FabPosition
import kr.lul.android.ui.scaffold.state.fab.FabState
import kr.lul.android.ui.scaffold.state.top.TopState
import kr.lul.android.ui.state.ProgressState

/**
 * [androidx.compose.material3.Scaffold]의 상태.
 *
 * @see androidx.compose.material3.Scaffold
 */
@Immutable
data class ScaffoldState(
    /**
     * 상단 바 상태.
     */
    val top: TopState = TopState.NONE,
    /**
     * 하단 바 상태.
     */
    val bottom: BottomState = BottomState.NONE,
    /**
     *  스낵바 상태.
     */
    val snackbar: SnackbarState = SnackbarState.NONE,
    /**
     * 플로팅 액션 버튼 상태.
     */
    val fab: FabState = FabState.NONE,
    /**
     * 플로팅 액션 버튼 위치.
     */
    val fabPosition: FabPosition = FabPosition.END,
    /**
     * 개발용 UI 상태.
     */
    val dev: DevState = DevState.NONE,
    /**
     * 진행 상태.
     */
    val progress: Set<ProgressState> = emptySet()
)
