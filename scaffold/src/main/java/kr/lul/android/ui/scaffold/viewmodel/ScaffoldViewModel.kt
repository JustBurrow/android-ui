package kr.lul.android.ui.scaffold.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kr.lul.android.ui.scaffold.pump.ScaffoldPump
import kr.lul.android.ui.scaffold.state.ScaffoldState
import kr.lul.android.ui.scaffold.state.SnackbarState
import kr.lul.android.ui.scaffold.state.bottom.BottomState
import kr.lul.android.ui.scaffold.state.dev.DevState
import kr.lul.android.ui.scaffold.state.fab.FabPosition
import kr.lul.android.ui.scaffold.state.fab.FabState
import kr.lul.android.ui.scaffold.state.top.TopState
import kr.lul.android.ui.state.ProgressState
import javax.inject.Inject

/**
 * [kr.lul.android.ui.scaffold.compose.Scaffold]의 상태를 관리하는 VM.
 *
 * 관리 항목 :
 * 1. 상단 바.
 * 2. 하단 바.
 * 3. 스낵바 호스트.
 * 4. 플로팅 액션 버튼.
 * 5. 플로팅 액션 버튼 위치.
 * 6. 개발자 도구.
 * 7. 진행 상태.
 */
@HiltViewModel
class ScaffoldViewModel @Inject constructor(
    private val pump: ScaffoldPump
) : ViewModel(), DefaultLifecycleObserver {
    companion object {
        private const val TAG = "ScaffoldViewModel"
    }

    @Suppress("UNCHECKED_CAST")
    val state = combine(
        pump.top,
        pump.bottom,
        pump.snackbar,
        pump.fab,
        pump.fabPosition,
        pump.dev,
        pump.progress
    ) {
        ScaffoldState(
            top = it[0] as TopState,
            bottom = it[1] as BottomState,
            snackbar = it[2] as SnackbarState,
            fab = it[3] as FabState,
            fabPosition = it[4] as FabPosition,
            dev = it[5] as DevState,
            progress = it[6] as Set<ProgressState>
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, ScaffoldState())

    override fun toString() = listOf(
        "state=${state.value}"
    ).joinToString(", ", "$TAG(", ")")
}